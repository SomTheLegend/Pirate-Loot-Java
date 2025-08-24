package in.stl.pirateloot;

import java.util.*;

/**
 * Represents the player character, the pirate.
 */
public class Pirate {

    String name;
    int health = 100;
    Weapon currentWeapon = Weapon.CUTLASS;
    int treasure = 0;
    Set<String> visitedTowns = new HashSet<>();
    Set<Weapon> weapons = new HashSet<>(Arrays.asList(Weapon.CUTLASS));

    /**
     * Constructs a new Pirate.
     *
     * @param name The name of the pirate.
     */
    public Pirate(String name) {
        this.name = name;
    }

    /**
     * Checks if the pirate is alive.
     *
     * @return True if the pirate is alive, false otherwise.
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Reduces the pirate's health by the given amount.
     *
     * @param damage The amount of damage to inflict.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            System.out.println("\nYou are dead. Game over.");
        }
    }

    /**
     * Heals the pirate by the given amount.
     *
     * @param amount The amount to heal.
     */
    public void heal(int amount) {
        this.health += amount;
        if (this.health > 100) this.health = 100;
    }

    /**
     * Adds a weapon to the pirate's inventory.
     *
     * @param weapon The weapon to acquire.
     */
    public void acquireWeapon(Weapon weapon) {
        weapons.add(weapon);
        System.out.println("\nYou've acquired a " + weapon.getLowercaseName() + "!");
    }

    /**
     * Allows the pirate to change their current weapon.
     *
     * @param scanner The scanner to use for user input.
     */
    public void changeWeapon(Scanner scanner) {

        if (weapons.size() == 1) {
            return;
        }
        System.out.println("\nChoose your weapon:");
        List<Weapon> weaponList = new ArrayList<>(weapons);

        for (int i = 0; i < weaponList.size(); i++) {
            Weapon w = weaponList.get(i);
            System.out.printf(("%d. %s " +
                    "(Damage: %d, Accuracy: %.0f%%)\n%n"), i + 1, w.getLowercaseName(),
                    w.damage, w.accuracy * 100);
        }
        System.out.print("Enter weapon number: ");

        try {
            int weaponNumber = Integer.parseInt(scanner.nextLine());
            if (weaponNumber > 0 && weaponNumber <= weapons.size()) {
                currentWeapon = weaponList.get(weaponNumber - 1);
                System.out.println("You've equipped the " + currentWeapon.getLowercaseName());
            }
            else {
                System.out.println("Invalid weapon number. Keeping your " +
                        currentWeapon.getLowercaseName());

            }
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Keeping your " +
                    currentWeapon.getLowercaseName());
        }
    }
}
