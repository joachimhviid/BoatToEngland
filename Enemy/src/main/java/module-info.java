module Enemy {
    requires com.almasb.fxgl.all;
    requires CommonEnemy;

    provides common.enemy.EnemySPI with enemysystem.EnemySpawner;
}