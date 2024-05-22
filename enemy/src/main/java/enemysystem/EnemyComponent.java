package enemysystem;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import common.ai.IPathFinder;
import javafx.geometry.Point2D;

import java.util.Optional;

public class EnemyComponent extends Component {
    private double speedX = 160;
    private double speedY = 160;
    private Optional<IPathFinder> pathFinder;

    public EnemyComponent() {
        this.pathFinder = Optional.empty();
    }

    public EnemyComponent(IPathFinder pathFinder) {
        this.pathFinder = Optional.ofNullable(pathFinder);
    }

    @Override
    public void onAdded() {
    }

    @Override
    public void onUpdate(double tpf) {
        pathFinder.ifPresentOrElse(pf -> {
            //Using pathfinder to get a direction here
            Point2D currentPos = new Point2D(entity.getX(), entity.getY());
            Point2D moveDirection = pf.getPath(currentPos).normalize();
            PhysicsComponent physics = entity.getComponent(PhysicsComponent.class);

            System.out.println(entity.getX() + " : " + entity.getY());

            physics.setBodyLinearVelocity(new Vec2(0,0));
            //            entity.translate(moveDirection.multiply(speedX * tpfda).getX(), moveDirection.multiply(speedY * tpf).getY());
            physics.setBodyLinearVelocity(new Vec2(moveDirection.getX(), moveDirection.getY() * -1).mul(speedX * tpf));
        }, () -> {
            //If there is no pathFinder I have just made it move in a constant direction for now
            moveAutonomously(tpf);
        });

    }

    public void moveAutonomously(double tpf) {
        double x = entity.getX();
        double y = entity.getY();

        double entityWidth = 30;
        double entityHeight = 30;

        double screenWidth = FXGL.getAppWidth();
        double screenHeight = FXGL.getAppHeight();

        if (x + entityWidth + (speedX * tpf) > screenWidth) {
            speedX = -Math.abs(speedX);
        }

        if (x + (speedX * tpf) < 0) {
            speedX = Math.abs(speedX);
        }

        if (y + entityHeight + (speedY * tpf) > screenHeight) {
            speedY = -Math.abs(speedY);
        }

        if (y + (speedY * tpf) < 0) {
            speedY = Math.abs(speedY);
        }

        entity.translateX(speedX * tpf);
        entity.translateY(speedY * tpf);
    }
}