open module core {
  requires common;
  requires com.almasb.fxgl.all;
    requires player;

    exports launcher to com.almasb.fxgl.core;
}