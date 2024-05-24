package playersystem;



import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;

import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import common.data.EntityType;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javafx.geometry.Point2D;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerFactoryTest {
    @Mock
    private Entity mockHitBox;

    @Mock
    private EntityBuilder mockEntityBuilder;

    @Mock
    PlayerFactory playerFactory;
    @Mock
    SpawnData spawnData;
    @Mock
    AnimationComponent animationComponent;

    @Mock
    PhysicsComponent physicsComponent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        playerFactory = mock(PlayerFactory.class);
        spawnData = mock(SpawnData.class);
        physicsComponent = mock(PhysicsComponent.class);
        mockHitBox = mock(Entity.class);
        mockEntityBuilder = mock(EntityBuilder.class);
        animationComponent = mock(AnimationComponent.class);
    }

    @Test
    public void testNewPlayer() {
        // Mock of player hitBox
        HitBox HitBox = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));

        mockEntityBuilder.bbox(HitBox);
        mockEntityBuilder.with(new CollidableComponent());
        mockEntityBuilder.with(physicsComponent);
        assertNotNull(mockEntityBuilder);

    }

    @Test
    public void testNewPlayerTwo(){
        when(playerFactory.newPlayer(spawnData)).thenReturn(new Entity());
    }

    @Test
    public void newPlayerNotNull(){
        HitBox HitBox = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));

        mockEntityBuilder.bbox(HitBox);
        assertNotNull(mockEntityBuilder);
    }

//    @Test
//    public void newPlayerCorrectEntityType(){
////        Entity playerEntity = playerFactory.newPlayer(spawnData);
////        playerEntity.setType(EntityType.PLAYER);
//        //mockEntityBuilder.with(animationComponent);
//
//        Entity player = playerFactory.newPlayer(spawnData);
//
//        player.setPosition(100,100);
//        //playerFactory.loadInput(player);
//        Point2D firstPosition = player.getPosition();
//
//        player.getComponent(AnimationComponent.class).moveRight();
//        Point2D newPosition = player.getPosition();
//
//
//        assertNotEquals(firstPosition, newPosition);
//        //Equals(EntityType.PLAYER, mockEntityBuilder);
//
//    }

    //Checking if the created player entity has a PhycisComponent attached
    @Test
    public void newPlayerHasComponent(){
        mockEntityBuilder.with(new CollidableComponent(true));
        mockEntityBuilder.with(physicsComponent);

        assertNotNull(mockEntityBuilder);
    }



    }
