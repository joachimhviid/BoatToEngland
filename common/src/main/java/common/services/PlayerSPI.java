package common.services;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public interface PlayerSPI {
  @Spawns("player")
  public Entity newPlayer(SpawnData data);

  public void loadInput(Entity player);
}
