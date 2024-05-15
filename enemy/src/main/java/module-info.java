module enemy {
    requires com.almasb.fxgl.all;
    requires common.enemy;
    requires common;
    requires common.ai;

    exports enemysystem to com.almasb.fxgl.core;
    provides common.enemy.EnemySPI with enemysystem.EnemyFactory;
}