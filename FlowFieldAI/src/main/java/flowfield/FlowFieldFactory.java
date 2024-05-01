package flowfield;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import common.ai.AI_SPI;
import common.ai.IPathFinder;
import common.ai.IPathFinderService;
import common.data.EntityType;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class FlowFieldFactory implements EntityFactory, AI_SPI, IPathFinderService {
    private FlowFieldGrid flowFieldGrid;

    public FlowFieldFactory() {
        flowFieldGrid = new FlowFieldGrid(17, 11, 100, new Point2D(100, 100));
    }

    @Override
    @Spawns("flowfield")
    public Entity createAI(SpawnData spawnData) {
        return entityBuilder(spawnData)
                .type(EntityType.FLOW_FIELD)
                .with(new FlowFieldComponent(flowFieldGrid))
                .buildAndAttach();
    }

    @Override
    public IPathFinder getPathFinder() {
        return flowFieldGrid;
    }
}
