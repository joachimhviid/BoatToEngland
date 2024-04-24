module weapon {
    uses services.WeaponSPI;
    exports weaponsystem;
    requires common;
    requires com.almasb.fxgl.all;
    requires com.almasb.fxgl.core;
    provides services.WeaponSPI with weaponsystem.WeaponFactory;
}
