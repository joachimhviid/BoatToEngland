package weaponsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.component.Component;
import services.WeaponSPI;

import java.util.List;
import java.util.ServiceLoader;

public class WeaponComponent extends Component {
    private Weapon weapon;

    private double speedX = 60;
    private double speedY = 20;

    public WeaponComponent() {
    }

    public WeaponComponent(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onUpdate(double tpf) {
        double x = entity.getX();
        double y = entity.getY();

        double entityWidth = 30;
        double entityHeight = 30;

        double screenWidth = FXGL.getAppWidth();
        double screenHeight = FXGL.getAppHeight();

        if (x + entityWidth + (speedX * tpf) > screenWidth) {
            speedX = -Math.abs(speedX);
        }

        if (x + (speedX * tpf) < 0) {
            speedX = Math.abs(speedX);
        }

        if (y + entityHeight + (speedY * tpf) > screenHeight) {
            speedY = -Math.abs(speedY);
        }

        if (y + (speedY * tpf) < 0) {
            speedY = Math.abs(speedY);
        }

        entity.translateX(speedX * tpf);
        entity.translateY(speedY * tpf);
    }

    public void playerAttack() {
        List<WeaponSPI> weaponFactories = ServiceLoader.load(WeaponSPI.class)
                .stream().map(ServiceLoader
                        .Provider::get)
                .toList();

        weaponFactories.forEach(WeaponFactory -> FXGL.getGameWorld().spawn("weapon"));
        System.out.println("We do be attacking");
    }
}
