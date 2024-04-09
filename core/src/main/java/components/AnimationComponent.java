package components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.ImagesKt;
import common.events.PlayerMovedEvent;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimationComponent extends Component {
    private int speedX = 0;
    private int speedY = 0;
    private int scale = 5;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animRun;

    public AnimationComponent() {
        Image playerReady = FXGL.image("player_ready.png");
        Image playerRun = FXGL.image("player_run.png");
        // TODO: Figure out a better way to resize images
        animIdle = new AnimationChannel(ImagesKt.resize(playerReady, (int) playerReady.getWidth() * scale, (int) playerReady.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(1), 0, 5);
        animRun = new AnimationChannel(ImagesKt.resize(playerRun, (int) playerRun.getWidth() * scale, (int) playerRun.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(1), 0, 5);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(speedX * tpf);
        entity.translateY(speedY * tpf);

        if (speedX != 0 || speedY != 0) {
            if (texture.getAnimationChannel() == animIdle) {
                texture.loopAnimationChannel(animRun);
            }
            speedX = (int) (speedX * 0.9);
            speedY = (int) (speedY * 0.9);

            if (FXGLMath.abs(speedX) < 1 && FXGLMath.abs(speedY) < 1) {
                speedX = 0;
                speedY = 0;
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    public void moveRight() {
        speedX = 150;
        entity.setScaleX(1);
        FXGL.getEventBus().fireEvent(new PlayerMovedEvent(new Point2D(entity.getX(), entity.getY())));
    }

    public void moveLeft() {
        speedX = -150;
        entity.setScaleX(-1);
        FXGL.getEventBus().fireEvent(new PlayerMovedEvent(new Point2D(entity.getX(), entity.getY())));
    }

    public void moveUp() {
        speedY = -150;
        FXGL.getEventBus().fireEvent(new PlayerMovedEvent(new Point2D(entity.getX(), entity.getY())));
    }

    public void moveDown() {
        speedY = 150;
        FXGL.getEventBus().fireEvent(new PlayerMovedEvent(new Point2D(entity.getX(), entity.getY())));
    }
}
