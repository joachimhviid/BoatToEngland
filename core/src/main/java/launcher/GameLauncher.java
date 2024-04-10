package launcher;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import data.EntityType;
import javafx.scene.input.KeyCode;
import playersystem.PlayerFactory;
import services.MapSPI;
import services.PlayerSPI;

import java.util.List;
import java.util.ServiceLoader;

import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;

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

        List<PlayerSPI> playerFactories = ServiceLoader.load(PlayerSPI.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .toList();

        playerFactories.forEach(factory -> {
            FXGL.getGameWorld().addEntityFactory((EntityFactory) factory);
            player = FXGL.getGameWorld().spawn("player", 100, 100);
            factory.loadInput(player);
        });

    // SpawnData = playerSpawnData; Optionally spawn data passed into newPlayer?

        Viewport viewport = FXGL.getGameScene().getViewport();
        viewport.setBounds(0, 0, 6400, 6400);
        viewport.bindToEntity(player, viewport.getWidth() / 2 - (double) (4 * 50) / 2, viewport.getHeight() / 2 - (double) (4 * 48) / 2);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ENEMY) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
                player.removeFromWorld();
            }
        });
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
