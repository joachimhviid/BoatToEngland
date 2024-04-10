module Enemy {
    requires com.almasb.fxgl.all;
    requires CommonEnemy;
    requires common;

    exports enemysystem to com.almasb.fxgl.core;
    provides common.enemy.EnemySPI with enemysystem.EnemyFactory;
}