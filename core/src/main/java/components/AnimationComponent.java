package components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.ImagesKt;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimationComponent extends Component {
    private int speed = 300;
    private int velocityX = 0;
    private int velocityY = 0;
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
        entity.translateX(velocityX * tpf);
        entity.translateY(velocityY * tpf);

        if (isMoving()) {
            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animRun);
            }
            // Deceleration
            velocityX = (int) (velocityX * 0.9);
            velocityY = (int) (velocityY * 0.9);

            if (FXGLMath.abs(velocityX) < 1 && FXGLMath.abs(velocityY) < 1) {
                velocityX = 0;
                velocityY = 0;
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    private boolean isMoving() {
        return velocityX != 0 || velocityY != 0;
    }

    public String getSpeed() {
        return "Speed: " + velocityX + ", " + velocityY;
    }

    private void normalizeSpeed() {
        if (velocityX != 0 && velocityY != 0) {
            velocityX /= (int) Math.sqrt(2);
            velocityY /= (int) Math.sqrt(2);
        }
    }

    public void moveRight() {
        velocityX = speed;
        entity.setScaleX(1);
    }

    public void moveLeft() {
        velocityX = -speed;
        entity.setScaleX(-1);
    }

    public void moveUp() {
        velocityY = -speed;
    }

    public void moveDown() {
        velocityY = speed;
    }
}
