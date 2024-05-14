import common.ai.AiSpi;

open module core {
	uses common.services.PlayerSPI;
	uses common.services.MapSPI;
    uses common.enemy.EnemySPI;
    uses AiSpi;
    uses common.ai.IPathFinder;

    requires common;
    requires com.almasb.fxgl.all;
    requires common.enemy;
    requires common.ai;
    requires player;

    exports launcher to com.almasb.fxgl.core;
}