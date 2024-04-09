open module core {
    uses common.enemy.EnemySPI;
    uses common.ai.AI_SPI;
    uses common.services.MapSPI;
    
    requires common;
    requires com.almasb.fxgl.all;
    requires CommonEnemy;
    requires CommonAI;

    exports launcher to com.almasb.fxgl.core;
}