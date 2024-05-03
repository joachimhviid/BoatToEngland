open module core {
	uses services.PlayerSPI;
	uses services.MapSPI;
    uses common.enemy.EnemySPI;
    uses common.ai.AI_SPI;
    uses common.ai.IPathFinder;

    requires common;
    requires com.almasb.fxgl.all;
    requires CommonEnemy;
    requires CommonAI;
    requires player;

    exports launcher to com.almasb.fxgl.core;
}