package launcher;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import components.AnimationComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class GameLauncher extends GameApplication {
  private Entity player;
  private Text speedometer;
  private Text viewport;

  public static void main(String[] args) {
    System.out.println("Hello World!");
    launch(args);
  }

  @Override
  protected void initSettings(GameSettings gameSettings) {
    gameSettings.setHeight(600);
    gameSettings.setWidthFromRatio(16 / 9.0);
    gameSettings.setTitle("Boat to England");
    gameSettings.setVersion("0.1");

    //gameSettings.setProfilingEnabled(true);
  }

  @Override
  protected void initInput() {
    Input input = FXGL.getInput();

    // See if these can be extracted to Player module
    input.addAction(new UserAction("Move Left") {
      protected void onAction() {
        System.out.println("Move left");
        player.getComponent(AnimationComponent.class).moveLeft();
      }
    }, KeyCode.A);
    input.addAction(new UserAction("Move Right") {
      protected void onAction() {
        System.out.println("Move right");
        player.getComponent(AnimationComponent.class).moveRight();
      }
    }, KeyCode.D);
    input.addAction(new UserAction("Move Up") {
      protected void onAction() {
        System.out.println("Move up");
        player.getComponent(AnimationComponent.class).moveUp();
      }
    }, KeyCode.W);
    input.addAction(new UserAction("Move Down") {
      protected void onAction() {
        System.out.println("Move down");
        player.getComponent(AnimationComponent.class).moveDown();
      }
    }, KeyCode.S);
  }

  @Override
  protected void initGame() {
    System.out.println("Game initialized");
    // Move to map module
    GameScene scene = FXGL.getGameScene();
    scene.setBackgroundColor(javafx.scene.paint.Color.LIGHTBLUE);
    FXGL.setLevelFromMap("boat-to-england-map.tmx");

    // Move to player module
    player = FXGL.entityBuilder()
        .with(new AnimationComponent())
        .bbox(new HitBox(BoundingShape.box(4 * 50, 4 * 48)))
        .buildAndAttach();

    Viewport viewport = scene.getViewport();
    viewport.setBounds(0, 0, 1600, 1600);
    viewport.bindToEntity(player, viewport.getWidth() / 2 - player.getWidth() / 2, viewport.getHeight() / 2 - player.getHeight() / 2);
  }

  @Override
  protected void initPhysics() {
    System.out.println("Physics initialized");
  }

  @Override
  protected void initUI() {
    System.out.println("UI initialized");
    speedometer = new Text();
    viewport = new Text();

    FXGL.addUINode(speedometer, 100, 100);
    FXGL.addUINode(viewport, 100, 150);
  }

  @Override
  protected void onUpdate(double tpf) {
    speedometer.setText(player.getComponent(AnimationComponent.class).getSpeed());
    viewport.setText("Viewport: %s, %s\nBounds: %s, %s\nPlayer dimensions: %s, %s".formatted(FXGL.getGameScene().getViewport().getWidth(), FXGL.getGameScene().getViewport().getHeight(), FXGL.getGameScene().getViewport().getWidth() / 2 - player.getWidth() / 2, FXGL.getGameScene().getViewport().getHeight() / 2 - player.getHeight() / 2, player.getWidth(), player.getHeight()));
  }
}
