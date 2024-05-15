open module core {
    requires common;
    requires com.almasb.fxgl.all;
    requires common.ai;
    requires common.enemy;
    uses common.services.PlayerSPI;
    uses common.services.MapSPI;
    uses common.services.WeaponSPI;
    uses common.ai.AiSpi;
    uses common.enemy.EnemySPI;


    exports launcher to com.almasb.fxgl.core;
}