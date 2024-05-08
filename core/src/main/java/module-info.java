open module core {
	uses common.services.PlayerSPI;
	uses common.services.MapSPI;
    uses common.enemy.EnemySPI;
    uses common.ai.AI_SPI;
    uses common.ai.IPathFinder;
    uses common.services.WaveSPI;

    requires common;
    requires com.almasb.fxgl.all;
    requires CommonEnemy;
    requires CommonAI;
    requires player;

    exports launcher to com.almasb.fxgl.core;
}