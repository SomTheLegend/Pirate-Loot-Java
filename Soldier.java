package in.stl.pirateloot;

/**
 * Represents a soldier enemy.
 */
public class Soldier extends Enemy{

    /**
     * Constructs a new Soldier.
     *
     * @param name     The name of the soldier.
     * @param health   The health of the soldier.
     * @param damage   The damage the soldier inflicts.
     * @param accuracy The accuracy of the soldier.
     */
    public Soldier(String name, int health, int damage, double accuracy) {
        super(name, health, damage, accuracy);
    }

}
