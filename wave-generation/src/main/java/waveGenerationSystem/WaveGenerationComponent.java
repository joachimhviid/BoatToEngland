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
    public void onUpdate(double tpf) {

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
                            FXGL.spawn("enemy", playerPosition.getX() + vp.getWidth()/2, FXGL.random(playerPosition.getY() - vp.getHeight()/2, playerPosition.getY() + vp.getHeight()/2));
                        } else if (side == 2){
                            FXGL.spawn("enemy", playerPosition.getX() - vp.getWidth()/2, FXGL.random(playerPosition.getY() - vp.getHeight()/2, playerPosition.getY() + vp.getHeight()/2));
                        } else if (side == 3){
                            FXGL.spawn("enemy", FXGL.random(playerPosition.getX() - vp.getWidth()/2, playerPosition.getX() + vp.getWidth()/2), playerPosition.getY() - vp.getHeight()/2);
                        } else {
                            FXGL.spawn("enemy", FXGL.random(playerPosition.getX() - vp.getWidth()/2, playerPosition.getX() + vp.getWidth()/2), playerPosition.getY() + vp.getHeight()/2);
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
