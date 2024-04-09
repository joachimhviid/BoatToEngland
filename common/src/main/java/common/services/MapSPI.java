package services;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public interface MapSPI {

    @Spawns("obstacle")
    Entity newObstacle(SpawnData data);

    void loadMap();
}
