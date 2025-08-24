package in.stl.pirateloot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a town that the pirate can visit.
 */
public class Town {

    String name;
    int treasureAmount;
    int minTreasureToLoot;
    List<Obstacle> obstacles;
    List<Enemy> enemies;
    private int lootedTreasure = 0;
    private final Random random = new Random();

    /**
     * Constructs a new Town.
     *
     * @param name              The name of the town.
     * @param treasureAmount    The total amount of treasure in the town.
     * @param minTreasureToLoot The minimum amount of treasure to loot.
     * @param obstacles         The obstacles in the town.
     * @param enemies           The enemies in the town.
     */
    public Town(String name, int treasureAmount, int minTreasureToLoot,
                List<Obstacle> obstacles, List<Enemy> enemies) {

        this.name = name;
        this.treasureAmount = treasureAmount;
        this.obstacles = obstacles;
        this.enemies = enemies;
        this.minTreasureToLoot = minTreasureToLoot;
    }

    /**
     * Checks if the town has been sufficiently looted.
     *
     * @return True if the town has been sufficiently looted, false otherwise.
     */
    public boolean isLootedSufficiently() {
        return lootedTreasure >= minTreasureToLoot;
    }

    /**
     * The pirate loots the town.
     *
     * @param pirate  The pirate looting the town.
     * @param scanner The scanner to use for user input.
     */
    public void loot(Pirate pirate, Scanner scanner) {

        System.out.println("\n--- Sailing to the town of " + name + " ---");
        System.out.println("This town is rumored to hold " + treasureAmount + " gold pieces.");
        System.out.println("You must collect at least " + minTreasureToLoot +
                "gold pieces to consider it sufficiently looted");
        pirate.visitedTowns.add(this.name);

        for (Obstacle obstacle : obstacles) {
            if (!pirate.isAlive()) break;
            obstacle.trigger(pirate);
        }

        if (!pirate.isAlive()) {
            System.out.println("You succumbed to the dangers of " + name + "...");
            return;
        }

        List<Enemy> currentEnemies = new ArrayList<>(this.enemies.stream()
                .map(e -> {
                    if (e instanceof Mercenary) return new Mercenary(
                            e.name, e.health, e.damage, e.accuracy);
                    if (e instanceof Soldier) return new Soldier(
                            e.name, e.health, e.damage, e.accuracy
                    );
                    return new Enemy(e.name, e.health, e.damage, e.accuracy);
                })
                .toList());

        while (pirate.isAlive() && !currentEnemies.isEmpty()) {
            Enemy enemy = currentEnemies.get(0);
            System.out.println("\nA wild " + enemy.name + " appears! " +
                    "(Health: " + enemy.health + ")");
            battle(pirate, enemy, scanner);

            if (pirate.isAlive()) {
                System.out.println("You defeated the " + enemy.name + "!");
                currentEnemies.remove(0);
            }
        }

        if (pirate.isAlive()) {
            int foundTreasure = random.nextInt(treasureAmount - minTreasureToLoot + 1)
                    + minTreasureToLoot;
            this.lootedTreasure = foundTreasure;
            pirate.treasure += foundTreasure;
            System.out.println("You found " + foundTreasure + " gold pieces " +
                    "from " + name + "!");
            System.out.println("Your total treasure is now " + pirate.treasure
                    + " gold pieces.");


            if (random.nextDouble() < 0.4) {
                System.out.println("You found a hidden stash!");
                int discovery = random.nextInt(3) + 1;

                switch (discovery) {
                    case 1:
                        System.out.println("It's a health potion! You feel reinvigorated");
                        pirate.heal(30);
                        break;
                    case 2:
                        System.out.println("You found a new weapon!");
                        Weapon newWeapon = Weapon.values()
                                [random.nextInt(Weapon.values().length)];
                        if (!pirate.weapons.contains(newWeapon)) {
                            pirate.acquireWeapon(newWeapon);
                        } else {
                            System.out.println("You already have a " +
                                    newWeapon.getLowercaseName() + "!" +
                                    " You leave it behind");
                        }
                        break;
                    case 3:
                        int extraTreasure = random.nextInt(51) + 50;
                        System.out.println("It's a bag of gold! You gain an extra " +
                                extraTreasure + " gold pieces");
                        pirate.treasure += extraTreasure;
                        System.out.println("Your total treasure is now " +
                                pirate.treasure + " gold pieces.");
                        break;
                }
            }
        } else {
            System.out.println("You were defeated in battle in " + name + "...");
        }
    }

    private void battle(Pirate pirate, Enemy enemy, Scanner scanner) {

        while (pirate.isAlive() && enemy.isAlive()) {
            pirate.changeWeapon(scanner);
            System.out.println("\nYour turn! What do you do?");
            System.out.println("1. Attack with " + pirate.currentWeapon.getLowercaseName());
            System.out.println("2. Try to flee");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if ("1".equals(choice)) {
                if (random.nextDouble() < pirate.currentWeapon.accuracy) {
                    int damageDealt = pirate.currentWeapon.damage;
                    enemy.takeDamage(damageDealt);
                    System.out.println("You hit the " + enemy.name + " for " + damageDealt
                            + " damage! Enemy health: " + enemy.health);
                } else {
                    System.out.println("You missed!");
                }
            } else if ("2".equals(choice)) {
                if (random.nextDouble() < 0.4) {
                    System.out.println("You successfully fled from the battle!");
                    return;
                } else {
                    System.out.println("You failed to flee!");
                }
            } else {
                System.out.println("Invalid choice, you hesitate and do nothing");
            }

            if (!enemy.isAlive()) continue;

            enemy.attack(pirate);
        }
    }
}
