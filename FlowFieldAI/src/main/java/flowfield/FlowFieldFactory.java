package flowfield;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import common.ai.AI_SPI;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class FlowFieldFactory implements EntityFactory, AI_SPI {
    @Override
    @Spawns("flowfield")
    public Entity createAI(SpawnData spawnData) {
        return entityBuilder(spawnData)
                //.type(BoatToEnglandType.FLOW_FIELD)
                .with(new FlowFieldComponent())
                .build();
    }
}
