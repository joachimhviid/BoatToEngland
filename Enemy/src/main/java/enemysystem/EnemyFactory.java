package enemysystem;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import common.enemy.Enemy;
import common.enemy.EnemySPI;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class EnemyFactory implements EntityFactory, EnemySPI {

    //THIS CAN BE DELETED LATER. Just saving it for now since I might have to use it
    //    @Spawns("enemy")
//    public Entity newEnemy(SpawnData spawnData) {
//        Enemy enemyData = new Enemy();
//
//        return entityBuilder(spawnData)
//                //.type(BoatToEnglandType.ENEMY --For when we have created the ENUMS
//                .view(new Rectangle(30, 30, Color.BLUE))
//                .with(new EnemyComponent(enemyData))
//                .build();
//    }

    @Override
    @Spawns("enemy")
    public Entity createEnemy(SpawnData spawnData) {
        Enemy enemyData = new Enemy();

        return entityBuilder(spawnData)
                //.type(BoatToEnglandType.ENEMY --For when we have created the ENUMS
                .view(new Rectangle(30, 30, Color.RED))
                .with(new EnemyComponent(enemyData))
                .build();

    }
}
