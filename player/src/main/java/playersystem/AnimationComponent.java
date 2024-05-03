package playersystem;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.ImagesKt;
import common.events.PlayerMovedEvent;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.net.URL;

public class AnimationComponent extends Component {
    private final double speed = 300;
    private Vec2 velocity = new Vec2(0, 0);
    private final int scale = 4;

    private PhysicsComponent physics;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animRun;

    //FOR TESTING
    private Point2D lastEventPosition = new Point2D(0,0);
    private final double CAP = 100;

    public AnimationComponent() {
        URL playerReadyUrl = url("textures/player_ready.png");
        if (playerReadyUrl == null) {
            throw new RuntimeException("Player ready image not found: " + "textures/player_ready.png");
        } else {
            Image playerReady = FXGL.image(playerReadyUrl);
            Image playerRun = FXGL.image(url("textures/player_run.png"));
            animIdle = new AnimationChannel(ImagesKt.resize(playerReady, (int) playerReady.getWidth() * scale, (int) playerReady.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(1), 0, 5);
            animRun = new AnimationChannel(ImagesKt.resize(playerRun, (int) playerRun.getWidth() * scale, (int) playerRun.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(1), 0, 5);

            texture = new AnimatedTexture(animIdle);
        }
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D((double) (50 * scale) / 2, (double) (48 * scale) / 2));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        normalizeSpeed();
        physics.setBodyLinearVelocity(velocity.mul(tpf * speed));

        if (physics.isMoving()) {
            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animRun);
            }
            // Deceleration
            velocity = velocity.mul(0.9);

            if (velocity.length() < 1) {
                velocity = velocity.mul(0);
            }

            Point2D currentPosition = new Point2D(entity.getX(), entity.getY());

            if(lastEventPosition.distance(currentPosition) > CAP) {
                lastEventPosition = currentPosition;

                FXGL.getEventBus().fireEvent(new PlayerMovedEvent(new Point2D(entity.getX(), entity.getY())));
            }

        } else if (texture.getAnimationChannel() == animRun) {
            texture.loopAnimationChannel(animIdle);
        }

    }

    private void normalizeSpeed() {
        if (velocity.length() > 0) {
            velocity = velocity.normalize();
        }
    }

    public void moveRight() {
        velocity = new Vec2(speed, velocity.y);
        entity.setScaleX(1);
        //
    }

    public void moveLeft() {
        velocity = new Vec2(-speed, velocity.y);
        entity.setScaleX(-1);
//        FXGL.getEventBus().fireEvent(new PlayerMovedEvent(new Point2D(entity.getX(), entity.getY())));
    }

    public void moveUp() {
        velocity = new Vec2(velocity.x, speed);
//        FXGL.getEventBus().fireEvent(new PlayerMovedEvent(new Point2D(entity.getX(), entity.getY())));
    }

    public void moveDown() {
        velocity = new Vec2(velocity.x, -speed);
//        FXGL.getEventBus().fireEvent(new PlayerMovedEvent(new Point2D(entity.getX(), entity.getY())));

    }

    private String getUrlPrefixForAssets() {
        return '/' + getClass().getModule().getName() + "/assets/";
    }

    private URL url(String path) {
        return getClass().getResource(getUrlPrefixForAssets() + path);
    }
}
