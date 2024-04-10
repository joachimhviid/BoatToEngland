package enemysystem;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.PhysicsComponent;
import common.enemy.Enemy;
import common.enemy.EnemySPI;
import common.data.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EnemyFactory implements EntityFactory, EnemySPI {

    @Override
    @Spawns("enemy")
    public Entity createEnemy(SpawnData spawnData) {
        Enemy enemyData = new Enemy();

        return entityBuilder(spawnData)
                .type(EntityType.ENEMY)
                .view(new Rectangle(30, 30, Color.RED))
                .with(new EnemyComponent(enemyData))
                .build();

    }
}
