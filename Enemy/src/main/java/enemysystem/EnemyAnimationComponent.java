package enemysystem;

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

    private final int scale = 2;


    public EnemyAnimationComponent() {

        URL enemyRunUrl = url("textures/enemy_run2.png");

        System.out.println(getUrlPrefixForAssets());
        System.out.println(enemyRunUrl);
        // /Users/tbechv/IdeaProjects/BoatToEngland/enemy/src/main/resources/enemy/assets/textures/priest_run.png
        if (enemyRunUrl == null) {
            throw new RuntimeException("Enemy ready image not found: " + enemyRunUrl);
        } else {
            Image enemyRun = FXGL.image(enemyRunUrl);
            enemyAnimRun = new AnimationChannel(ImagesKt.resize(enemyRun, (int) enemyRun.getWidth() * scale, (int) enemyRun.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(1), 0, 5);

            texture = new AnimatedTexture(enemyAnimRun);
        }
    }


    @Override
    public void onAdded() {

        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(enemyAnimRun);
    }


    private String getUrlPrefixForAssets() {
        return '/' + getClass().getModule().getName().toLowerCase() + "/assets/";
    }

    private URL url(String path) {
        return getClass().getResource(getUrlPrefixForAssets() + path);
    }


}

