package common.services;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public interface WeaponSPI {
    @Spawns("weapon")
    public Entity createWeapon(SpawnData data);

    public void loadWeapon(Entity weapon);
}