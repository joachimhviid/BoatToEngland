module player {
    exports playersystem;
    requires weapon;
    requires com.almasb.fxgl.all;
    requires common;

    opens playersystem to com.almasb.fxgl.core;

    provides services.PlayerSPI with playersystem.PlayerFactory;

}