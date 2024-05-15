open module core {
    requires common;
    requires com.almasb.fxgl.all;
    uses common.services.PlayerSPI;
    uses common.services.MapSPI;
    uses common.enemy.EnemySPI;
    uses common.ai.AI_SPI;
    uses common.ai.IPathFinder;
    uses common.services.WeaponSPI;

    requires CommonEnemy;
    requires CommonAI;

    exports launcher to com.almasb.fxgl.core;
}