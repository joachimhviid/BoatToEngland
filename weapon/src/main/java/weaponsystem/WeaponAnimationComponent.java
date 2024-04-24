package weaponsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.ImagesKt;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.net.URL;

public class WeaponAnimationComponent extends Component {

    private AnimatedTexture texture;
    private AnimationChannel animIdle;

    private final int scale = 2;


    public WeaponAnimationComponent() {

        URL axeURL = url("textures/axe_attack_spin_v1.png");
        if (axeURL == null) {
            throw new RuntimeException("axeURL not found: " + axeURL);
        } else {
            Image axeIMG = FXGL.image(axeURL);
            animIdle = new AnimationChannel(ImagesKt.resize(axeIMG, (int) axeIMG.getWidth() * scale, (int) axeIMG.getHeight() * scale), 6, 50 * scale, 48 * scale, Duration.seconds(0.3), 0, 5);

            texture = new AnimatedTexture(animIdle);
        }

    }




    @Override
    public void onAdded() {

        entity.getViewComponent().addChild(texture);
        texture.loopAnimationChannel(animIdle);
    }


    private String getUrlPrefixForAssets() {
        return '/' + getClass().getModule().getName() + "/assets/";
    }

    private URL url(String path) {
        return getClass().getResource(getUrlPrefixForAssets() + path);
    }


}
