package net.canarymod.api.statistics;

import com.google.common.collect.Maps;
import net.canarymod.Canary;

import java.util.HashMap;

/**
 * Achievements list
 *
 * @author Jason (darkdiplomat)
 */
public enum Achievements {

    OPENINVENTORY("openInventory"),
    GETTINGWOOD("mineWood"),
    BENCHMARKING("buildWorkBench"),
    TIMETOMINE("buildPickaxe"),
    HOTTOPIC("buildFurnace"),
    ACQUIREHARDWARE("acquireIron"),
    TIMETOFARM("buildHoe"),
    BAKEBREAD("makeBread"),
    THELIE("bakeCake"),
    GETTINGAUPGRADE("buildBetterPickaxe"),
    DELICIOUSFISH("cookFish"),
    ONARAIL("onARail"),
    TIMETOSTRIKE("buildSword"),
    MONSTERHUNTER("killEnemy"),
    COWTIPPER("killCow"),
    WHENPIGFLY("flyPig"),
    SNIPERDUEL("snipeSkeleton"),
    DIAMONDS("diamonds"),
    DIAMONDSTOYOU("diamondsToYou"),
    WENEEDTOGODEEPER("portal"),
    RETURNTOSENDER("ghast"),
    INTOFIRE("blazeRod"),
    POTION("potion"),
    THEEND("theEnd"),
    THEEND2("theEnd2"),
    ENCHANTER("enchantments"),
    OVERKILL("overkill"),
    LIBRARIAN("bookcase"),
    REPOPULATION("breedCow"),
    THEBEGINNING("spawnWither"),
    THEBEGINNING2("killWither"),
    BEACONATOR("fullBeacon"),
    ADVENTURINGTIME("exploreAllBiomes"),
    OVERPOWERED("overpowered"),;

    private final String nmsName;
    private final static HashMap<String, Achievements> achievementsMap = Maps.newHashMap();

    private Achievements(String nmsName) {
        this.nmsName = "achievement.".concat(nmsName);
    }

    /**
     * Gets the full native name of the {@code Achievements}
     *
     * @return full native name
     */
    public final String getNativeName() {
        return nmsName;
    }

    /**
     * Gets the {@link net.canarymod.api.statistics.Achievement} instance for the given {@code Achievements}
     *
     * @return {@link net.canarymod.api.statistics.Achievement} instance
     */
    public final Achievement getInstance() {
        return Canary.factory().getStatisticsFactory().getAchievement(nmsName);
    }

    /**
     * Gets an {@code Achievements} for the native name
     *
     * @param nmsName
     *         the native Minecraft name
     *
     * @return {@code Achievements} matching the name or {@code null} if not match found
     */
    public static Achievements forNMSName(final String nmsName) {
        String temp = nmsName;
        if (!nmsName.startsWith("achievement.")) {
            temp = "achievement.".concat(nmsName);
        }
        return achievementsMap.get(temp);
    }

    static {
        for (Achievements achievment : Achievements.values()) {
            achievementsMap.put(achievment.nmsName, achievment);
        }
    }
}
