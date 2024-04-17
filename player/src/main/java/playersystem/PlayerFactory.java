package playersystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import data.EntityType;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import services.PlayerSPI;

public class PlayerFactory implements EntityFactory, PlayerSPI {

    // Is this right?
    @Spawns("player")
    public Entity newPlayer(SpawnData data) {

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        HitBox box = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));
        return FXGL.entityBuilder(data)
                .type(EntityType.PLAYER)
                .with(physics)
                .bbox(box)
                .with(new PlayerComponent())
                .with(new CollidableComponent(true))
                .with(new AnimationComponent())
                .buildAndAttach();
    }

    public void loadInput(Entity player) {

    }

}
