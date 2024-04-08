package components;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.ImagesKt;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimationComponent extends Component {
    private final double speed = 300;
    private Vec2 velocity = new Vec2(0, 0);
    private final int scale = 4;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animRun;

    public AnimationComponent() {
        Image playerReady = FXGL.image("player_ready.png");
        Image playerRun = FXGL.image("player_run.png");
        // TODO: Figure out a better way to resize images maybe based on application window size
        animIdle = new AnimationChannel(ImagesKt.resize(playerReady, (int) playerReady.getWidth() * scale, (int) playerReady.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(1), 0, 5);
        animRun = new AnimationChannel(ImagesKt.resize(playerRun, (int) playerRun.getWidth() * scale, (int) playerRun.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(1), 0, 5);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D((double) (50 * scale) / 2, (double) (48 * scale) / 2));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        normalizeSpeed();
        entity.translate(velocity.mul(tpf * speed));

        if (isMoving()) {
            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animRun);
            }
            // Deceleration
            velocity = velocity.mul(0.9);

            if (velocity.length() < 1) {
                velocity = velocity.mul(0);
            }
        } else if (texture.getAnimationChannel() == animRun) {
            texture.loopAnimationChannel(animIdle);
        }
    }

    private boolean isMoving() {
        return velocity.length() > 0;
    }

    private void normalizeSpeed() {
        if (velocity.length() > 0) {
            velocity = velocity.normalize();
        }
    }

    public void moveRight() {
        velocity = new Vec2(speed, velocity.y);
        entity.setScaleX(1);
    }

    public void moveLeft() {
        velocity = new Vec2(-speed, velocity.y);
        entity.setScaleX(-1);
    }

    public void moveUp() {
        velocity = new Vec2(velocity.x, -speed);
    }

    public void moveDown() {
        velocity = new Vec2(velocity.x, speed);
    }
}
