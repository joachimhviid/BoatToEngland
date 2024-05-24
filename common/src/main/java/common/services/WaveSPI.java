package common.services;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public interface WaveSPI {
    @Spawns("WaveGeneration")
    public Entity newWaveGeneration (SpawnData data);
}
