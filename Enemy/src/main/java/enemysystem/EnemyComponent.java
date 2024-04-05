package enemysystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import common.enemy.Enemy;

public class EnemyComponent extends Component {
    private Enemy enemy;
    private double speedX = 160;
    private double speedY = 20;

    public EnemyComponent(Enemy enemy) {
        this.enemy = enemy;
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
}
