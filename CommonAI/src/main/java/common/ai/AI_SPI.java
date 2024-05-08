package common.ai;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

public interface AI_SPI {
    Entity createAI(SpawnData spawnData);
}
