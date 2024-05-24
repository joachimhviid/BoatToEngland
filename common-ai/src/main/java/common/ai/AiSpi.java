package common.ai;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;

public interface AiSpi {
    Entity createAI(SpawnData spawnData);

    IPathFinder getPathFinder();
}
