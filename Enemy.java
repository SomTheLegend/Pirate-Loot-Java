package in.stl.pirateloot;

import java.util.Random;

/**
 * Represents an enemy that the pirate can fight.
 */
public class Enemy {

    String name;
    int health;
    int damage;
    double accuracy;
    Random random = new Random();

    /**
     * Constructs a new Enemy.
     *
     * @param name     The name of the enemy.
     * @param health   The health of the enemy.
     * @param damage   The damage the enemy inflicts.
     * @param accuracy The accuracy of the enemy.
     */
    public Enemy(String name, int health, int damage, double accuracy) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.accuracy = accuracy;
    }

    /**
     * Checks if the enemy is alive.
     *
     * @return True if the enemy is alive, false otherwise.
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Reduces the enemy's health by the given amount.
     *
     * @param damage The amount of damage to inflict.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * The enemy attacks the pirate.
     *
     * @param pirate The pirate to attack.
     */
    public void attack(Pirate pirate) {

        System.out.println("\n" + name + "'s turn!");

        if (random.nextDouble() < accuracy) {
            pirate.takeDamage(this.damage);
            System.out.println("The " + name + " hit you for " + this.damage
                    + " damage. Your health is now " + pirate.health + ".");
        } else {
            System.out.println("The " + name + " missed!");
        }
    }
}
