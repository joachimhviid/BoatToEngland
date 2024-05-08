module weapon {
    uses common.services.WeaponSPI;
    exports weaponsystem;
    requires common;
    requires com.almasb.fxgl.all;
    requires com.almasb.fxgl.core;
    opens weaponsystem to com.almasb.fxgl.core;
    provides common.services.WeaponSPI with weaponsystem.WeaponFactory;
}
