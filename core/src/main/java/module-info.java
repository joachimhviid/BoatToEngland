open module core {
    uses services.MapSPI;
  uses services.PlayerSPI;
  requires common;
  requires com.almasb.fxgl.all;
    requires player;

    exports launcher to com.almasb.fxgl.core;
}