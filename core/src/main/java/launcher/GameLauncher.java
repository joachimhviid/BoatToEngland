package launcher;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import components.AnimationComponent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class GameLauncher extends GameApplication {
  private Entity player;

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
  }

  @Override
  protected void initInput() {
    Input input = FXGL.getInput();
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
      }
    }, KeyCode.W);
    input.addAction(new UserAction("Move Down") {
      protected void onAction() {
        System.out.println("Move down");
      }
    }, KeyCode.S);
  }

  @Override
  protected void initGame() {
    System.out.println("Game initialized");
    GameScene scene = FXGL.getGameScene();
    scene.setBackgroundColor(javafx.scene.paint.Color.LIGHTBLUE);
    player = FXGL.entityBuilder()
      .at(400, 300)
      .with(new AnimationComponent())
      .buildAndAttach();
  }

  @Override
  protected void initPhysics() {
    System.out.println("Physics initialized");
  }

  @Override
  protected void initUI() {
    System.out.println("UI initialized");
      Text text = new Text("Hello World!");

      FXGL.addUINode(text, 100, 100);
  }
}
