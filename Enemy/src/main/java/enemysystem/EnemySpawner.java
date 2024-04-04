package enemysystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import common.enemy.EnemySPI;

public class EnemySpawner implements EnemySPI {
    private EnemyFactory enemyFactory;

    @Override
    public void createEnemy(SpawnData spawnData) {
        FXGL.getGameWorld().addEntity(enemyFactory.newEnemy(spawnData));
    }
}
