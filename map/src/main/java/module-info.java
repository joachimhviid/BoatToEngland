module map {
    requires com.almasb.fxgl.all;
    requires common;

    exports mapsystem to com.almasb.fxgl.core;
    opens map.assets.levels to com.almasb.fxgl.all;

    provides common.services.MapSPI with mapsystem.WorldFactory;
}