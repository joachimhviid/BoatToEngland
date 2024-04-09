package components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.geometry.Point2D;

public class ObstacleFactory implements EntityFactory {
    // Polygon bounding boxes currently isn't working in FXGL
    //@Spawns("water")
    //public Entity newWater(SpawnData data) {
    //    List<Double> points = ((Polygon) data.get("polygon")).getPoints();
    //
    //    return FXGL.entityBuilder(data)
    //        .bbox(new HitBox(BoundingShape.polygonFromDoubles(points)))
    //        .with(new PhysicsComponent())
    //        .build();
    //}

    @Spawns("obstacle")
    public Entity newObstacle(SpawnData data) {
        return FXGL.entityBuilder(data)
            .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
            .with(new PhysicsComponent())
            .build();
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        HitBox box = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));

        return FXGL.entityBuilder(data)
            .type(EntityType.PLAYER)
            .bbox(box)
            .with(physics)
            .with(new AnimationComponent())
            .build();
    }
}
