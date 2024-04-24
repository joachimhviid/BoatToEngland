package playersystem;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import weaponsystem.Weapon;
import weaponsystem.WeaponComponent;

public class PlayerComponent extends Component {

    int health;
    int damage;

    int movementSpeed;
    Weapon weapon;


    public PlayerComponent() {

        Input input = FXGL.getInput();

        input.addAction(new UserAction("Move Left") {
            protected void onAction() {
                entity.getComponent(AnimationComponent.class).moveLeft();
            }
        }, KeyCode.A);
        input.addAction(new UserAction("Move Right") {
            protected void onAction() {
                entity.getComponent(AnimationComponent.class).moveRight();
            }
        }, KeyCode.D);
        input.addAction(new UserAction("Move Up") {
            protected void onAction() {
                entity.getComponent(AnimationComponent.class).moveUp();
            }
        }, KeyCode.W);
        input.addAction(new UserAction("Move Down") {
            protected void onAction() {
                entity.getComponent(AnimationComponent.class).moveDown();
            }
        }, KeyCode.S);
        input.addAction(new UserAction("Attack") {
            protected void onActionBegin() {
//                entity.getComponent(WeaponComponent.class).weaponAttack(entity);
                Vec2 playervelocity = entity.getComponent(AnimationComponent.class).getVelocity();
                FXGL.getGameWorld().spawn("weapon", new SpawnData(entity.getCenter())
                        .put("direction", playervelocity.toPoint2D()));

            }
        }, KeyCode.SPACE);

        // Optional
        input.addAction(new UserAction("Shout") {
            protected void onActionBegin() {
                entity.getComponent(AnimationComponent.class).shout();
                FXGL.play("shout1.wav");
                //Note: sounds files must be put under /assets/sounds in the core resources
            }
        }, KeyCode.F);


        // TODO: Add attack move
//        input.addAction(new UserAction("Attack Move") {
//            protected void onAction() {
//                entity.getComponent(AnimationComponent.class).moveDown();
//            }
//        }, KeyCode.Å);


    }


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }


    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public void wasAttacked(int damage) {
        setHealth(getHealth() - damage);
        if (getHealth() <= 0) {
            death();
        }
    }


    public void death() {
        //Implement game over
    }


}
