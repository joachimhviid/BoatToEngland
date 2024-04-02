package playersystem;

import weaponsystem.Weapon;

public class Player {


    int health;
    int damage;

    int movementSpeed;
    Weapon weapon;




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
