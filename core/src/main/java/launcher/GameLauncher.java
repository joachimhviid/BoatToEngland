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
import common.ai.AiSpi;
import common.data.EntityType;
import common.services.MapSPI;
import common.services.PlayerSPI;
import common.services.WeaponSPI;
import common.services.WaveSPI;
import javafx.scene.input.KeyCode;
import common.ai.IPathFinder;
import common.data.ServiceRegistry;
import common.enemy.EnemySPI;
import common.events.DebugToggleEvent;

import java.util.List;
import java.util.ServiceLoader;

import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;

public class GameLauncher extends GameApplication {
    private Entity player;

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

        List<PlayerSPI> playerFactories = ServiceLoader.load(PlayerSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();

        playerFactories.forEach(factory -> {
            FXGL.getGameWorld().addEntityFactory((EntityFactory) factory);
            player = FXGL.getGameWorld().spawn("player", 3000, 3000);
            factory.loadInput(player);
        });

        List<WeaponSPI> weaponFactories = ServiceLoader.load(WeaponSPI.class)
                .stream().map(ServiceLoader
                        .Provider::get)
                .toList();

        weaponFactories.forEach(WeaponFactory -> FXGL.getGameWorld().addEntityFactory((EntityFactory) WeaponFactory));

        ServiceLoader<AiSpi> aiFactory = ServiceLoader.load(AiSpi.class);
        aiFactory.stream().forEach(aiSpiProvider -> {
            AiSpi service = aiSpiProvider.get();
            IPathFinder pathFinder = service.getPathFinder();
            if (pathFinder != null) {
                ServiceRegistry.registerService(IPathFinder.class, pathFinder);
            }

            FXGL.getGameWorld().addEntityFactory((EntityFactory) service);
            FXGL.getGameWorld().spawn("flowfield");

        });

        List<EnemySPI> enemyFactories = ServiceLoader.load(EnemySPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();

        enemyFactories.forEach(enemyFactory -> {
            FXGL.getGameWorld().addEntityFactory((EntityFactory) enemyFactory);
            //FXGL.getGameWorld().spawn("enemy");
        });


        List<WaveSPI> waveFactories = ServiceLoader.load(WaveSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .toList();

        waveFactories.forEach(waveFactory -> {
            FXGL.getGameWorld().addEntityFactory((EntityFactory) waveFactory);
            FXGL.getGameWorld().spawn("wave");
        });


        Viewport viewport = FXGL.getGameScene().getViewport();
        viewport.setBounds(0, 0, 6400, 6400);

        if (player != null) {
            viewport.bindToEntity(player, viewport.getWidth() / 2 - (double) (4 * 50) / 2, viewport.getHeight() / 2 - (double) (4 * 48) / 2);
        }

        //Music loading
        //FXGL.play("background_music.mp3");
        System.out.println("Background music is playing");

    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.ENEMY) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
//                player.removeFromWorld();
            }
        });
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.WEAPON, EntityType.ENEMY) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity weapon, Entity enemy) {
                weapon.removeFromWorld();
                enemy.removeFromWorld();
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
