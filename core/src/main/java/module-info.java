open module core {
    uses services.MapSPI;
    requires common;
  requires com.almasb.fxgl.all;

  exports launcher to com.almasb.fxgl.core;
}