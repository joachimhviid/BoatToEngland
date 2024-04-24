package weaponsystem;

import com.almasb.fxgl.dsl.FXGL;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import services.WeaponSPI;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;


public class WeaponFactory implements EntityFactory, WeaponSPI {
    @Override
    @Spawns("weapon")
    public Entity createWeapon(SpawnData data) {
        Weapon axe = new Weapon(0.5, 5);
        return entityBuilder(data)
                .view(new Rectangle(30, 30, Color.BLUE))
                .with(new WeaponComponent(axe))
                .build();

       /*
        PhysicsComponent physics = new PhysicsComponent();

        physics.setBodyType(BodyType.DYNAMIC);
        HitBox box = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));
        return FXGL.entityBuilder(data)
                .type(EntityType.WEAPON)
                .with(physics)
                .bbox(box)
                .with(new WeaponComponent(axe))
                .with(new CollidableComponent(true))
                .view(new Rectangle(20, 20, Color.BLUE))
                .buildAndAttach();

        */

    }

    @Override
    public void loadWeapon(Entity weapon) {

    }
}
