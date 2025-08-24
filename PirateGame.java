package in.stl.pirateloot;

import java.util.*;

/**
 * The main game class.
 */
public class PirateGame {

    private static final List<Level> levels = new ArrayList<>();
    private static final Random random = new Random();

    static {

        levels.add(new Level(1, Arrays.asList(
                new Town("Port Blossom", 100, 50,
                        List.of(new Obstacle("QuickSand", 5)),
                        List.of(new Soldier("Town Guard", 40,
                                10, 0.7))),

                new Town("Sandy Creek", 120, 60,
                        List.of(new Obstacle("Falling Coconut", 5)),
                        List.of(new Soldier("Town Guard", 45,
                                15, 0.7))),

                new Town("Whispering Isle", 150, 70,
                        List.of(new Obstacle("Poisonous Spider", 10)),
                        List.of(new Soldier("Veteran Guard", 50,
                                15, 0.75)))

        )));

        levels.add(new Level(2, Arrays.asList(
                new Town("Gator's End", 200, 100,
                        List.of(new Obstacle("Snapping Crocodile", 15)),
                        List.of(new Mercenary("Swamp Mercenary", 60,
                                20, 0.65))),

                new Town("Swamp Foot", 220, 110,
                        List.of(new Obstacle("Leech-infested water", 10)),
                        List.of(new Soldier("Swamp Patroller", 55,
                                15, 0.8), new Mercenary("Swamp " +
                                "Mercenary", 60, 20, 0.65))),

                new Town("Misty Mangrove", 250, 120,
                        List.of(new Obstacle("Sudden Sinkhole", 15),
                                new Obstacle("Snapping Crocodile", 15)),
                        List.of(new Mercenary("Elite Mercenary", 70,
                                25, 0.75)))

        )));

        levels.add(new Level(3, Arrays.asList(
                new Town("Serpent's Coil", 300, 150,
                        List.of(new Obstacle("Venomous Snake", 20)),
                        List.of(new Mercenary("Jungle Assassin", 80,
                                25, 0.75))),

                new Town("Viper's Nest", 320, 160,
                        List.of(new Obstacle("Spike Trap", 25)),
                        List.of(new Soldier("Temple Guard", 70,
                                20, 0.8), new Mercenary("Jungle " +
                                "Assassin", 80, 25, 0.75))),

                new Town("Poisoned Spring", 350, 170,
                        List.of(new Obstacle("Dart Trap", 25),
                                new Obstacle("Venomous Snake", 20)),
                        List.of(new Mercenary("Temple Executioner", 90, 30, 0.8)))

        )));

        levels.add(new Level(4, Arrays.asList(
                new Town("Fort Courage", 400, 200,
                        List.of(new Obstacle("Landmine", 30)),
                        List.of(new Soldier("Fortress Soldier", 100,
                                30, 0.8), new Soldier("Garrison " +
                                "Captain", 100, 40, 0.85))),

                new Town("Cannonball Bay", 450, 220,
                        List.of(new Obstacle("Stray Cannonball", 35)),
                        List.of(new Soldier("Cannoneer", 110,
                                35, 0.8), new Mercenary("Fortress " +
                                "Mercenary", 120, 35, 0.75))),

                new Town("The Garrison", 500, 250,
                        List.of(new Obstacle("Barbed Wire", 25),
                                new Obstacle("Snapping Crocodile", 15)),
                        List.of(new Soldier("Garrison Captain", 150,
                                45, 0.85), new Soldier("Elite "
                        + "Guard", 100, 40, 0.8)))
        )));

        levels.add(new Level(5, Arrays.asList(
                new Town("Jaguar Jungle", 550, 270,
                        List.of(new Obstacle("Shadow Cat Pounce", 35)),
                        List.of(new Mercenary("Jungle Stalker", 140,
                                40, 0.8))),

                new Town("The Lost Ruins", 600, 300,
                        List.of(new Obstacle("Crumbling Floor", 25),
                                new Obstacle("Ancient Curse", 15)),
                        List.of(new Soldier("Undead Soldier", 130,
                                35, 0.75), new Mercenary("Ruin " +
                                "Guardian", 160, 45, 0.8))),

                new Town("Temple of Fangs", 650, 320,
                        List.of(new Obstacle("Swinging Blade Trap",40)),
                        List.of(new Mercenary("High Priest", 180,
                                45, 0.85), new Mercenary("Temple " +
                                "Fanatic", 160, 45, 0.8)))
        )));

        levels.add(new Level(6, Arrays.asList(
                new Town("Volcano's Heart", 800, 400,
                        List.of(new Obstacle("Lava Geyser", 50)),
                        List.of(new Enemy("Lava Elemental", 200,
                                50, 0.75))),

                new Town("The Obsidian Fortress", 900, 450,
                        List.of(new Obstacle("Obsidian Shards", 50)),
                        List.of(new Mercenary("Obsidian Guard", 160, 40,
                                0.8))),

                new Town("The Mad King's Treasury", 1500, 750,
                        List.of(new Obstacle("Royal Treasury Trap", 60),
                                new Obstacle("Snapping Crocodile", 15)),
                        List.of(new Mercenary("The Mad King", 500,
                                65, 0.9), new Soldier("Royal Guard",
                                200, 50, 0.85)))

        )));
    }

    /**
     * Starts the game.
     */
    public static void start() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Pirate's Loot!");
        System.out.print("Enter your pirate's name: ");
        String pirateName = scanner.nextLine();
        if (pirateName.trim().isEmpty()) {
            pirateName = "Captain Fearless";
        }

        Pirate pirate = new Pirate(pirateName);
        System.out.println("Welcome, Captain " + pirate.name + "!");

        for (Level level : levels) {
            System.out.println("\n--- Entering Level " + level.levelNumber + " ---");

            while (!level.areAllTownsVisitedAndLooted(pirate) && pirate.isAlive()) {
                System.out.println("\nCaptain " + pirate.name + " your stats:");
                System.out.println("Health: " + pirate.health);
                System.out.println("Treasure: " + pirate.treasure);
                System.out.println("Weapon: " + pirate.currentWeapon.getLowercaseName());
                System.out.println("\nTowns in this level:");

                for (int i = 0; i < level.towns.size(); i++) {
                    Town town = level.towns.get(i);
                    String visitedMark = "";
                    if (pirate.visitedTowns.contains(town.name)) {
                        visitedMark = town.isLootedSufficiently() ? "[LOOTED]" : "[VISITED]";
                    }
                    System.out.printf("%d. %s %s\n", i + 1, town.name, visitedMark);
                }
                System.out.print("Choose a town to loot (enter number): ");

                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice > 0 && choice <= level.towns.size()) {
                        Town town = level.towns.get(choice - 1);
                        if (pirate.visitedTowns.contains(town.name) && town.isLootedSufficiently()) {
                            System.out.println("You've already visited and sufficiently " +
                                    "looted " + town.name + ".");
                        } else {
                            town.loot(pirate, scanner);

                        }
                    }
                    else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            if (!pirate.isAlive()) {
                System.out.println("Your journey ends here, Captain " + pirate.name + ".");
                System.out.println("Final Treasure: " + pirate.treasure);
                scanner.close();
                return;
            }

            System.out.println("\nCongratulations! You've conquered Level " +
                    level.levelNumber + "!");

            if (level.levelNumber < levels.size()) {
                System.out.println("You found a treasure map to a hidden weapon cache!");
                List<Weapon> availableWeapons = Arrays.stream(Weapon.values())
                        .filter(w -> !pirate.weapons.contains(w))
                        .toList();

                if (!availableWeapons.isEmpty()) {
                    pirate.acquireWeapon(availableWeapons.get
                            (random.nextInt(availableWeapons.size())));
                }
                else {
                    System.out.println("...but it seems you already have all the weapons from it.");
                }
                System.out.println("You also rest and recover your health.");
                pirate.heal(60);
            }
        }

        System.out.println("\n--- VICTORY! ---");
        System.out.println("You have conquered all the levels and become the most feared " +
                "pirate on the seven seas!");
        System.out.println("Final Treasure: " + pirate.treasure);
        scanner.close();
    }
}
