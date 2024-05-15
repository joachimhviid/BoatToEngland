open module core {
    uses common.services.PlayerSPI;
    uses common.services.MapSPI;
    uses common.enemy.EnemySPI;
    uses common.ai.AiSpi;
    uses common.ai.IPathFinder;
    uses common.services.WaveSPI;
    uses common.services.WeaponSPI;

    requires common;
    requires com.almasb.fxgl.all;
    requires common.enemy;
    requires common.ai;

    exports launcher to com.almasb.fxgl.core;
}