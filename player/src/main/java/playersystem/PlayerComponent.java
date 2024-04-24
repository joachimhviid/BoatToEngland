package playersystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import weaponsystem.Weapon;

public class PlayerComponent extends Component {


    int health;
    int damage;

    int movementSpeed;
    Weapon weapon;

    boolean autoFireEnabled;
    //TODO:
    //Implement autoFireInterval get firerate from weapon
    private double autoFireInterval = 0.7; // Interval between autofire in seconds
    private double autoFireTimer = 0.0;
    private Point2D currentPlayerVelocity = new Point2D(0, 0);
    private Point2D oldPlayerVelocity = new Point2D(0, 0);


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

                if (autoFireEnabled) {
                    autoFireEnabled = false;
                } else if (!autoFireEnabled) {
                    autoFireTimer = 0.5;
                    autoFireEnabled = true;
                }

            }
        }, KeyCode.SPACE);


        input.addAction(new UserAction("Shout") {
            protected void onActionBegin() {
                entity.getComponent(AnimationComponent.class).shout();
                FXGL.play("shout1.wav");
                //Note: sounds files must be put under /assets/sounds in the core resources
            }
        }, KeyCode.F);


    }


    @Override
    public void onUpdate(double tpf) {

        if (autoFireEnabled) {
            autoFireTimer += tpf;
            if (autoFireTimer >= autoFireInterval) {
                autoFireTimer = 0.0;
                attack();
            }
        }
    }

    private void attack() {

        currentPlayerVelocity = entity.getComponent(AnimationComponent.class).getVelocity().toPoint2D();
        if (!currentPlayerVelocity.equals(Point2D.ZERO)) {
            oldPlayerVelocity = currentPlayerVelocity;
        }

        Point2D weaponSpawnData = new Point2D(entity.getX() + 60, entity.getY() + 60);
        FXGL.getGameWorld().spawn("weapon", new SpawnData(weaponSpawnData)
                .put("direction", oldPlayerVelocity));

        // entity.getComponent(WeaponComponent.class).weaponAttack();
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
