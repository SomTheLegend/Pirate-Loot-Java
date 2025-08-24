package in.stl.pirateloot;

/**
 * Represents a mercenary enemy.
 */
public class Mercenary extends Enemy{

    /**
     * Constructs a new Mercenary.
     *
     * @param name     The name of the mercenary.
     * @param health   The health of the mercenary.
     * @param damage   The damage the mercenary inflicts.
     * @param accuracy The accuracy of the mercenary.
     */
    public Mercenary(String name, int health, int damage, double accuracy) {
        super(name, health, damage, accuracy);
    }

    /**
     * The mercenary attacks the pirate.
     *
     * @param pirate The pirate to attack.
     */
    @Override
    public void attack(Pirate pirate) {

        System.out.println("\n" + name + "'s turn!");

        if (random.nextDouble() < accuracy) {
            int damageDealt = this.damage;
            if (random.nextDouble() < 0.20) {
                damageDealt *= 2;
                System.out.println("The " + name + " lands a critical hit!");
            }
            pirate.takeDamage(damageDealt);
            System.out.println("The " + name + " hit you for " + damageDealt
                    + " damage. Your health is now " + pirate.health + ".");
        }
        else {
            System.out.println("The " + name + " missed!");
        }
    }
}
