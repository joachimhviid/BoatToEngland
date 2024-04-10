package launcher;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import common.ai.AI_SPI;
import common.enemy.EnemySPI;
import common.events.DebugToggleEvent;
import components.AnimationComponent;
import common.data.EntityType;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import common.services.MapSPI;

import java.util.List;
import java.util.ServiceLoader;

import java.util.List;
import java.util.ServiceLoader;

public class GameLauncher extends GameApplication {
    private Entity player;

    //private Text debugText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setHeight(600);
        gameSettings.setWidthFromRatio(16 / 9.0);
        gameSettings.setTitle("Boat to England");
        gameSettings.setVersion("0.1");

        //gameSettings.setProfilingEnabled(true);
        gameSettings.setTicksPerSecond(60);
        // Press 1 to open
        gameSettings.setDeveloperMenuEnabled(true);
    }

    @Override
    protected void initInput() {
        Input input = FXGL.getInput();

        // See if these can be extracted to Player module
        input.addAction(new UserAction("Move Left") {
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveLeft();
            }
        }, KeyCode.A);
        input.addAction(new UserAction("Move Right") {
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveRight();
            }
        }, KeyCode.D);
        input.addAction(new UserAction("Move Up") {
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveUp();
            }
        }, KeyCode.W);
        input.addAction(new UserAction("Move Down") {
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveDown();
            }
        }, KeyCode.S);
        input.addAction(new UserAction("Toggle FlowField Visibility") {
            @Override
            protected void onActionBegin() {
                FXGL.getEventBus().fireEvent(new DebugToggleEvent());
            }
        }, KeyCode.COMMA);
    }

    @Override
    protected void initGame() {
        List<MapSPI> worldFactories = ServiceLoader.load(MapSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();

        worldFactories.forEach(factory -> {
            System.out.println("Found world factory: " + factory);
            FXGL.getGameWorld().addEntityFactory((EntityFactory) factory);
            factory.loadMap();
        });

        List<EnemySPI> enemyFactories = ServiceLoader.load(EnemySPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();

        enemyFactories.forEach(enemyFactory -> FXGL.getGameWorld().addEntityFactory((EntityFactory) enemyFactory));
        enemyFactories.forEach(enemyFactory -> FXGL.getGameWorld().spawn("enemy"));

        ServiceLoader<AI_SPI> aiFactory = ServiceLoader.load(AI_SPI.class);
        aiFactory.stream().forEach(aiSpiProvider -> {
            AI_SPI service = aiSpiProvider.get();
            FXGL.getGameWorld().addEntityFactory((EntityFactory) service);
        });

        FXGL.getGameWorld().spawn("flowfield");

        // Move to player module
        //player = FXGL.getGameWorld().getEntitiesByType(EntityType.PLAYER).getFirst();
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        HitBox box = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));

        player = FXGL.entityBuilder()
                .at(100, 100)
                .type(EntityType.PLAYER)
                .bbox(box)
                .with(physics)
                .with(new AnimationComponent())
                .buildAndAttach();

        Viewport viewport = FXGL.getGameScene().getViewport();
        viewport.setBounds(0, 0, 6400, 6400);
        viewport.bindToEntity(player, viewport.getWidth() / 2 - (double) (4 * 50) / 2, viewport.getHeight() / 2 - (double) (4 * 48) / 2);

    }

    @Override
    protected void initPhysics() {
        System.out.println("Physics initialized");
    }

    @Override
    protected void initUI() {
        System.out.println("UI initialized");
        // debugText = new Text();
        // FXGL.addUINode(debugText, 100, 100);
    }

    @Override
    protected void onUpdate(double tpf) {
        // debugText.setText(ticks++ + " ticks\nTPF: " + tpf);
    }
}
