package playersystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

//@SetEntityFactory
public class PlayerFactory implements EntityFactory {

    // Is this right?
    @Spawns("player")
//  public Entity newPlayer(SpawnData data) {
    public Entity newPlayer() {
        return FXGL.entityBuilder()
                .with(new AnimationComponent())
                .buildAndAttach();
    }

}
