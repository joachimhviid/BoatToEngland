package common.enemy;

import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Entity;

public interface EnemySPI {
    Entity createEnemy(SpawnData spawnData);

}
