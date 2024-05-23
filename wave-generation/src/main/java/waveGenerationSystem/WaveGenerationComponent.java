package waveGenerationSystem;

import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import common.data.EntityType;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.core.math.FXGLMath.random;

public class WaveGenerationComponent extends Component {
    public int roundNumber = 0;
    public int difficulty = 1;

    private boolean hasEnemyFactory = true;

    @Override
    public void onUpdate(double tpf) {

        if (FXGL.getGameWorld().getEntitiesByType(EntityType.ENEMY).isEmpty() && hasEnemyFactory) {
            roundNumber++;
            int enemyCount = 1 + roundNumber * difficulty;
            try {
                for (int i = 0; i < enemyCount; i++) {
                    int wavePattern = FXGL.random(1, 2);
                    System.out.println("Wave pattern: " + wavePattern);
                    if (wavePattern == 1) {
                        int waveSize = 1 + roundNumber;
                        for (int j = 0; j < waveSize; j++) {
                            //Gets a screen side to spawn the enemy
                            double[] spawnPosition = spawnPosition();
                            FXGL.spawn("enemy", spawnPosition[0], spawnPosition[1]);
                        }
                    } else {
                        double[] spawnPosition = spawnPosition();
                        FXGL.spawn("enemy", spawnPosition[0], spawnPosition[1]);
                        if (spawnPosition[2] == 1) {
                            FXGL.spawn("enemy", spawnPosition[0] + 25, spawnPosition[1] + 50);
                            FXGL.spawn("enemy", spawnPosition[0] + 25, spawnPosition[1] - 50);
                        }else if (spawnPosition[2] == 2) {
                            FXGL.spawn("enemy", spawnPosition[0] - 25, spawnPosition[1] + 50);
                            FXGL.spawn("enemy", spawnPosition[0] - 25, spawnPosition[1] - 50);
                        }else if (spawnPosition[2] == 3) {
                            FXGL.spawn("enemy", spawnPosition[0] - 50, spawnPosition[1] - 50);
                            FXGL.spawn("enemy", spawnPosition[0] + 50, spawnPosition[1] - 50);
                        } else {
                            FXGL.spawn("enemy", spawnPosition[0] - 50, spawnPosition[1] + 50);
                            FXGL.spawn("enemy", spawnPosition[0] + 50, spawnPosition[1] + 50);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Enemy factory not found");
                hasEnemyFactory = false;
            }
        }
    }

    private double[] spawnPosition() {
        Point2D playerPosition = FXGL.getGameWorld().getSingleton(EntityType.PLAYER).getPosition();
        Viewport vp = FXGL.getGameScene().getViewport();
        int side = FXGL.random(1, 4);
        double[] spawnPosition;
        if (side == 1) {
            //spawn on right side
            double x = playerPosition.getX() + vp.getWidth() / 2;
            double y = FXGL.random(playerPosition.getX() - vp.getWidth() / 2, playerPosition.getX() + vp.getWidth() / 2);
            spawnPosition = new double[]{x, y, side};
        } else if (side == 2) {
            //spawn on left side
            double x = playerPosition.getX() - vp.getWidth() / 2;
            double y = FXGL.random(playerPosition.getY() - vp.getHeight() / 2, playerPosition.getY() + vp.getHeight() / 2);
            spawnPosition = new double[]{x, y, side};
        } else if (side == 3) {
            //spawn on top side
            double x = playerPosition.getY() - vp.getHeight() / 2;
            double y = FXGL.random(playerPosition.getX() - vp.getWidth() / 2, playerPosition.getX() + vp.getWidth() / 2);
            spawnPosition = new double[]{x, y};
        } else {
            //spawn on bottom side
            double x = playerPosition.getY() + vp.getHeight() / 2;
            double y = FXGL.random(playerPosition.getX() - vp.getWidth() / 2, playerPosition.getX() + vp.getWidth() / 2);
            spawnPosition = new double[]{x, y, side};
        }
        return spawnPosition;
    }

}
