open module core {
    uses services.MapSPI;
  uses services.PlayerSPI;
    uses services.WeaponSPI;
    requires common;
  requires com.almasb.fxgl.all;

    exports launcher to com.almasb.fxgl.core;
}