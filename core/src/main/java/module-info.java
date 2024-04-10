open module core {
    uses services.MapSPI;
    requires common;
  requires com.almasb.fxgl.all;
    requires player;

    exports launcher to com.almasb.fxgl.core;
}