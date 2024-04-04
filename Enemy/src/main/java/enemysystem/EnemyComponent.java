package enemysystem;

import com.almasb.fxgl.entity.component.Component;
import common.enemy.Enemy;

public class EnemyComponent extends Component {
    private Enemy enemy;

    public EnemyComponent(Enemy enemy) {
        this.enemy = enemy;
    }
    @Override
    public void onAdded() {
    }

    @Override
    public void onUpdate(double tpf) {

    }
}
