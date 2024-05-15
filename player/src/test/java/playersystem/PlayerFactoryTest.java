package playersystem;



import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(FXGL.entityBuilder()).thenReturn(mockEntityBuilder);
    }

    @Test
    public void testNewPlayer() {

        // instantiating playerfactory to call newPlayer method
        PlayerFactory playerFactory = new PlayerFactory();

        // Mock of SpawnData for the newPlayer method
        SpawnData mockSpawnData = new SpawnData();

        // Mock a physics component player entity
        PhysicsComponent mockPhysics = new PhysicsComponent();
        mockPhysics.setBodyType(BodyType.DYNAMIC);

        // Mock of player hitBox
        HitBox mockHitBox = new HitBox(new Point2D((double) (4 * 50) / 4, (double) (4 * 48) / 5), BoundingShape.box(2 * 50, 3 * 48));

        //Mocking the use of FXGL.entityBuilder().buildAndAttach();

        Entity mockEntity = new Entity();
        mockEntityBuilder.buildAndAttach();
        when(mockEntityBuilder.type(EntityType.PLAYER)).thenReturn(mockEntityBuilder);
        when(mockEntityBuilder.bbox(mockHitBox)).thenReturn(mockEntityBuilder);
        when(mockEntityBuilder.with(any())).thenReturn(mockEntityBuilder);
        when(mockEntityBuilder.buildAndAttach()).thenReturn(mockEntity);


        // Call the method to be tested
        Entity playerEntity = playerFactory.newPlayer(mockSpawnData);

        // checking if the mockEntity is equal to the playerentity creating using the entitybuilder directly
        assertEquals(mockEntity, playerEntity);

    }

    @Test
    public void testNewPlayerTwo(){
        playerFactory.newPlayer(spawnData);
        when(playerFactory.newPlayer(spawnData)).thenReturn(new Entity());

    }

    @Test
    public void newPlayerNotNull(){
        Entity playerEntity = playerFactory.newPlayer(spawnData);
        assertNotNull(playerEntity, "The player entity should not be null");

    }

    @Test
    public void newPlayerCorrectEntityType(){
        Entity playerEntity = playerFactory.newPlayer(spawnData);
        assertEquals(EntityType.PLAYER, playerEntity.getType());
    }

    //Checking if the created player entity has a PhycisComponent attached
    @Test
    public void newPlayerHasComponent(){
        Entity playerEntity = playerFactory.newPlayer(spawnData);
        //mock.
        assertTrue(playerEntity.hasComponent(PhysicsComponent.class));
    }



    }
