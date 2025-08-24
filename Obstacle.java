package in.stl.pirateloot;

/**
 * Represents an obstacle that a pirate can encounter.
 */
public class Obstacle {

    String name;
    int damage;

    /**
     * Constructs a new Obstacle.
     *
     * @param name   The name of the obstacle.
     * @param damage The damage the obstacle inflicts.
     */
    public Obstacle(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    /**
     * Triggers the obstacle's effect on the pirate.
     *
     * @param pirate The pirate who encounters the obstacle.
     */
    public void trigger(Pirate pirate) {

        System.out.println("Watch out! You've encountered a " + name + "!");
        pirate.takeDamage(damage);
        System.out.println("You took " + damage + " damage." +
                " Your health is now " + pirate.health + ".");
        if (pirate.health < 50 && pirate.isAlive()) {
            System.out.println("You're badly wounded! You should search for a " +
                    "health potion before fighting another enemy.");
        }
    }
}













