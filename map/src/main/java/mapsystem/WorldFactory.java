package mapsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import data.EntityType;
import services.MapSPI;

import java.net.URL;

public class WorldFactory implements EntityFactory, MapSPI {
    @Override
    @Spawns("obstacle")
    public Entity newObstacle(SpawnData data) {
        return FXGL.entityBuilder(data)
            .type(EntityType.OBSTACLE)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .build();
    }

    @Override
    public void loadMap() {
        URL levelUrl = url("levels/boat-to-england-map-4x.tmx");
        if (levelUrl == null) {
            throw new RuntimeException("Level not found: " + "levels/boat-to-england-map-4x.tmx");
        } else {
            TMXLevelLoader levelLoader = new TMXLevelLoader();
            Level level = levelLoader.load(levelUrl, FXGL.getGameWorld());
            FXGL.getGameWorld().setLevel(level);
            FXGL.getPhysicsWorld().setGravity(0, 0);
        }
    }

    private String getUrlPrefixForAssets() {
        return '/' + getClass().getModule().getName() + "/assets/";
    }

    private URL url(String path) {
        return getClass().getResource(getUrlPrefixForAssets() + path);
    }

    //@Spawns("player")
    //public Entity newPlayer(SpawnData data) {
    //    PhysicsComponent physics = new PhysicsComponent();
    //    physics.setBodyType(BodyType.DYNAMIC);
    //    HitBox box = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));
    //
    //    return FXGL.entityBuilder(data)
    //        .type(EntityType.PLAYER)
    //        .bbox(box)
    //        .with(physics)
    //        //.with(new AnimationComponent())
    //        .build();
    //}
}
