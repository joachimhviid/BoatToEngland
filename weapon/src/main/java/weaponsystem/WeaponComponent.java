package weaponsystem;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import services.WeaponSPI;

import java.util.List;
import java.util.ServiceLoader;

public class WeaponComponent extends Component {
    private Weapon weapon;

    private double speed = 60;
    private Point2D direction = Point2D.ZERO;
    private PhysicsComponent physicsComponent;


    public WeaponComponent() {
    }

    public WeaponComponent(Weapon weapon, Point2D direction) {
        this.weapon = weapon;
        this.direction = direction;
    }

    @Override
    public void onAdded() {
        System.out.println("spawned a weapon");

        //TODO: Add axe sound effect, not important!
        //      FXGL.play();
    }

    @Override
    public void onUpdate(double tpf) {

//        Old enemy movement
//        double x = entity.getX();
//        double y = entity.getY();
//
//        double entityWidth = 30;
//        double entityHeight = 30;
//
//        double screenWidth = FXGL.getAppWidth();
//        double screenHeight = FXGL.getAppHeight();
//
//        if (x + entityWidth + (speedX * tpf) > screenWidth) {
//            speedX = -Math.abs(speedX);
//        }
//
//        if (x + (speedX * tpf) < 0) {
//            speedX = Math.abs(speedX);
//        }
//
//        if (y + entityHeight + (speedY * tpf) > screenHeight) {
//            speedY = -Math.abs(speedY);
//        }
//
//        if (y + (speedY * tpf) < 0) {
//            speedY = Math.abs(speedY);
//        }

        physicsComponent.setLinearVelocity(direction.multiply(speed));

    }

    public void weaponAttack(Entity player) {
//        List<WeaponSPI> weaponFactories = ServiceLoader.load(WeaponSPI.class)
//                .stream().map(ServiceLoader
//                        .Provider::get)
//                .toList();

//        weaponFactories.forEach(WeaponFactory -> {
        FXGL.getGameWorld().spawn("weapon", new SpawnData(player.getCenter().multiply(4))
                        .put("direction", Vec2.fromAngle(player.getRotation() - 90).toPoint2D()));
//        });
        System.out.println("Weapon spawned");
    }
}
