package weaponsystem;

import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import data.EntityType;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import services.WeaponSPI;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;


public class WeaponFactory implements EntityFactory, WeaponSPI {

    @Override
    @Spawns("weapon")
    public Entity createWeapon(SpawnData data) {
        Weapon weapon = new Weapon(0.5, 5);

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        Point2D dir = data.get("direction");
        // Dir test
        System.out.println(dir);

        return entityBuilder(data)
            .type(EntityType.WEAPON)
            .with(physics)
            .bbox(new HitBox(BoundingShape.box(25, 25)))
            .with(new CollidableComponent(true))
            .with(new WeaponComponent(weapon, dir))
            .with(new WeaponAnimationComponent())
            .with(new OffscreenCleanComponent())
            .with(new ExpireCleanComponent(Duration.seconds(3)))
            .build();

    }

    @Override
    public void loadWeapon(Entity weapon) {

    }
}
