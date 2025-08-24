package in.stl.pirateloot;

/**
 * Represents the weapons available in the game.
 */
public enum Weapon {
    CUTLASS(15, 0.85),
    PISTOL(30, 0.65),
    BLUNDERBUSS(50, 0.45),
    CANNON(100, 0.25);

    final int damage;
    final double accuracy;

    /**
     * Constructs a new Weapon.
     *
     * @param damage   The damage the weapon inflicts.
     * @param accuracy The accuracy of the weapon.
     */
    Weapon(int damage, double accuracy) {
        this.damage = damage;
        this.accuracy = accuracy;
    }

    /**
     * Returns the lowercase name of the weapon.
     *
     * @return The lowercase name of the weapon.
     */
    public String getLowercaseName() {
        return this.name().toLowerCase();
    }
}
