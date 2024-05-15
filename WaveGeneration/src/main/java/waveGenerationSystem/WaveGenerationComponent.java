package waveGenerationSystem;

import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import common.data.EntityType;
import javafx.geometry.Point2D;

import java.util.List;

public class WaveGenerationComponent extends Component {
    public int roundNumber = 0;
    public int difficulty = 1;

    @Override
    public void onAdded() {
        System.out.println("Wave generation component added");
    }
    @Override
    public void onUpdate(double tpf) {
        System.out.println("Wave generation component");
        if (FXGL.getGameWorld().getEntitiesByType(EntityType.ENEMY).size() == 0) {
            Point2D playerPosition = FXGL.getGameWorld().getSingleton(EntityType.PLAYER).getPosition();
            Viewport vp = FXGL.getGameScene().getViewport();
            roundNumber++;
            int enemyCount = 5 + roundNumber * difficulty;
            for (int i = 0; i < enemyCount; i++) {
                int wavePattern = FXGL.random(1, 2);
                System.out.println("Wave pattern: " + wavePattern);
                if (wavePattern == 1) {
                    int waveSize = 1 + roundNumber;
                    for (int j = 0; j < waveSize; j++) {
                        //Gets a screen side to spawn the enemy
                        int side = FXGL.random(1, 4);
                        if (side == 1){
                            FXGL.spawn("enemy", vp.getX() + vp.getWidth(), FXGL.random(vp.getY(), vp.getY() + vp.getHeight()));
                        } else if (side == 2){
                            FXGL.spawn("enemy", vp.getX(), FXGL.random(vp.getY(), vp.getY() + vp.getHeight()));
                        } else if (side == 3){
                            FXGL.spawn("enemy", FXGL.random(vp.getX(), vp.getX() + vp.getWidth()), vp.getY());
                        } else {
                            FXGL.spawn("enemy", FXGL.random(vp.getX(), vp.getX() + vp.getWidth()), vp.getY() + vp.getHeight());
                        }
                    }
                } else {
                    //Should spawn the enemy in a certain pattern
                    FXGL.spawn("enemy", 500, 100);
                }
                FXGL.spawn("enemy");
            }
        }
    }

}
