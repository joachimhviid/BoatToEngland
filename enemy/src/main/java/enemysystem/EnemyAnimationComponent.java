package enemysystem;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.ImagesKt;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.net.URL;

public class EnemyAnimationComponent extends Component {

    private AnimatedTexture texture;
    private AnimationChannel enemyAnimRun;

    private final double speed = 300;
    private Vec2 direction = new Vec2(0, 0);
    private Vec2 velocity = new Vec2(0, 0);

    private final int scale = 2;


    public EnemyAnimationComponent() {

        URL enemyRunUrl = url("textures/enemy_run2.png");

        if (enemyRunUrl == null) {
            throw new RuntimeException("Enemy ready image not found: " + enemyRunUrl);
        } else {
            Image enemyRun = FXGL.image(enemyRunUrl);
            enemyAnimRun = new AnimationChannel(ImagesKt.resize(enemyRun, (int) enemyRun.getWidth() * scale, (int) enemyRun.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(1), 0, 5);

            texture = new AnimatedTexture(enemyAnimRun);
        }
    }

    @Override
    public void onUpdate(double tpf) {

        normalizeSpeed();
        direction = velocity.mul(tpf * speed);

        System.out.println(direction.x);
        System.out.println(direction.y);

        if (direction.x < 0) {
            entity.setScaleX(1);
        }
        if (direction.x > 0) {
            entity.setScaleX(-1);
        }


    }

    @Override
    public void onAdded() {

        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(enemyAnimRun);
    }

    private void normalizeSpeed() {
        if (velocity.length() > 0) {
            velocity = velocity.normalize();
        }
    }


    private String getUrlPrefixForAssets() {
        return '/' + getClass().getModule().getName().toLowerCase() + "/assets/";
    }

    private URL url(String path) {
        return getClass().getResource(getUrlPrefixForAssets() + path);
    }


}

