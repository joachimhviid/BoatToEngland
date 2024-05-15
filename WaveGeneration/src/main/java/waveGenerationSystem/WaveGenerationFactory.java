package waveGenerationSystem;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import common.services.WaveSPI;


public class WaveGenerationFactory implements EntityFactory, WaveSPI {
    @Override
    @Spawns("wave")
    public Entity newWaveGeneration(SpawnData data){
        return FXGL.entityBuilder(data)
                .with(new WaveGenerationComponent())
                .build();
    }
}
