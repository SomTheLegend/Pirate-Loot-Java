package in.stl.pirateloot;

import java.util.List;

/**
 * Represents a level in the game.
 */
public class Level {

    int levelNumber;
    List<Town> towns;

    /**
     * Constructs a new Level.
     *
     * @param levelNumber The level number.
     * @param towns       The towns in the level.
     */
    public Level(int levelNumber, List<Town> towns) {
        this.levelNumber = levelNumber;
        this.towns = towns;
    }

    /**
     * Checks if all towns in the level have been visited and sufficiently looted.
     *
     * @param pirate The pirate to check.
     * @return True if all towns have been visited and sufficiently looted, false otherwise.
     */
    public boolean areAllTownsVisitedAndLooted(Pirate pirate) {
        for (Town town : towns) {
            if (!pirate.visitedTowns.contains(town.name) || !town.isLootedSufficiently()) {
                return false;
            }
        }
        return true;
    }
}
