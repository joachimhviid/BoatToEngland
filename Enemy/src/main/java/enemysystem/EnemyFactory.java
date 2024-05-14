package enemysystem;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import common.ai.IPathFinder;
import common.data.ServiceRegistry;
import common.enemy.EnemySPI;
import common.data.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EnemyFactory implements EntityFactory, EnemySPI {
    @Override
    @Spawns("enemy")
    public Entity createEnemy(SpawnData spawnData) {
        Optional<IPathFinder> pathFinder = ServiceRegistry.getService(IPathFinder.class);
        if (pathFinder.isPresent()) {
            System.out.println("PathFinder successfully retrieved for EnemyComponent");
        } else {
            System.out.println("Failed to retrieve PathFinder for EnemyComponent");
        }

        return entityBuilder(spawnData)
                .type(EntityType.ENEMY)
                .view(new Rectangle(30, 30, Color.RED))
                .with(new EnemyComponent(pathFinder.orElse(null)))
                .buildAndAttach();

    }
}
