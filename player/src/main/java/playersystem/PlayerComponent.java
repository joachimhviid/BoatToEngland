package playersystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import common.data.EntityType;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class PlayerComponent extends Component {


    int health;
    int damage;

    int movementSpeed;

    boolean autoFireEnabled;
    //TODO:
    //Implement autoFireInterval get firerate from weapon
    public double autoFireInterval = 0.7; // Interval between autofire in seconds
    public double autoFireTimer = 0.0;
    private Point2D currentPlayerVelocity = new Point2D(0, 0);
    private Point2D oldPlayerVelocity = new Point2D(0, 0);

    private Entity getPlayer() {
        return FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
    }

    public PlayerComponent() {

        Input input = FXGL.getInput();
        try {
            input.addAction(new UserAction("Move Left") {
                protected void onAction() {
                    getPlayer().getComponent(AnimationComponent.class).moveLeft();
                }
            }, KeyCode.A);
            input.addAction(new UserAction("Move Right") {
                protected void onAction() {
                    getPlayer().getComponent(AnimationComponent.class).moveRight();
                }
            }, KeyCode.D);
            input.addAction(new UserAction("Move Up") {
                protected void onAction() {
                    getPlayer().getComponent(AnimationComponent.class).moveUp();
                }
            }, KeyCode.W);
            input.addAction(new UserAction("Move Down") {
                protected void onAction() {
                    getPlayer().getComponent(AnimationComponent.class).moveDown();
                }
            }, KeyCode.S);
            input.addAction(new UserAction("Attack") {
                protected void onActionBegin() {

                    if (getPlayer().getComponent(PlayerComponent.class).autoFireEnabled) {
                        getPlayer().getComponent(PlayerComponent.class).autoFireEnabled = false;
                    } else if (!getPlayer().getComponent(PlayerComponent.class).autoFireEnabled) {
                        getPlayer().getComponent(PlayerComponent.class).autoFireTimer = 0.5;
                        getPlayer().getComponent(PlayerComponent.class).autoFireEnabled = true;
                    }

                }
            }, KeyCode.SPACE);


            input.addAction(new UserAction("Shout") {
                protected void onActionBegin() {
                    getPlayer().getComponent(AnimationComponent.class).shout();
                    FXGL.play("shout1.wav");
                    //Note: sounds files must be put under /assets/sounds in the core resources
                }
            }, KeyCode.F);
        } catch (Exception e) {
            System.out.println("PlayerComponent input already bound");
        }



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

        currentPlayerVelocity = getPlayer().getComponent(AnimationComponent.class).getVelocity().toPoint2D();
        if (!currentPlayerVelocity.equals(Point2D.ZERO)) {
            oldPlayerVelocity = currentPlayerVelocity;
        }

        try {
        Point2D weaponSpawnData = new Point2D(getPlayer().getX() + 60, getPlayer().getY() + 60);
        FXGL.getGameWorld().spawn("weapon", new SpawnData(weaponSpawnData)
                .put("direction", oldPlayerVelocity));
        } catch (Exception e) {
            System.out.println("Weapon spawn failed");
        }

        // entity.getComponent(WeaponComponent.class).weaponAttack();
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
