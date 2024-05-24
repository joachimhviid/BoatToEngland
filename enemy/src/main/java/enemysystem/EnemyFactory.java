package enemysystem;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import common.ai.IPathFinder;
import common.data.ServiceRegistry;
import common.enemy.EnemySPI;
import common.data.EntityType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EnemyFactory implements EntityFactory, EnemySPI {
    @Override
    @Spawns("enemy")
    public Entity createEnemy(SpawnData spawnData) {
        Optional<IPathFinder> pathFinder = ServiceRegistry.getService(IPathFinder.class);

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        CollidableComponent collidable = new CollidableComponent(true);
        HitBox box = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));

        return entityBuilder(spawnData)
                .type(EntityType.ENEMY)
                .with(new EnemyComponent(pathFinder.orElse(null)))
                .with(physics)
                .bbox(box)
                .with(collidable)
//                .view(new Rectangle(30, 30, Color.RED))
                .with(new EnemyAnimationComponent())
                .buildAndAttach();

    }
}
