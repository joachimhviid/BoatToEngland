open module core {
    uses common.enemy.EnemySPI;
    requires common;
    requires com.almasb.fxgl.all;
    requires CommonEnemy;

    exports launcher to com.almasb.fxgl.core;
}