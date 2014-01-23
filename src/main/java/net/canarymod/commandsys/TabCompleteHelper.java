package net.canarymod.commandsys;

import net.canarymod.Canary;
import net.canarymod.api.world.DimensionType;

import java.util.ArrayList;
import java.util.List;

/**
 * A set of methods to help with doing tab completes
 *
 * @author Jason (darkdiplomat)
 */
public final class TabCompleteHelper {

    /**
     * Matches a partial {@link String} to a possible matching {@link String}
     *
     * @param partial
     *         the partial {@link String}
     * @param possible
     *         the possible matching {@link String}
     *
     * @return {@code true} if matches or if partial is empty; {@code false} if not
     */
    public static boolean startsWith(String partial, String possible) {
        return possible.regionMatches(true, 0, partial, 0, partial.length());
    }

    /**
     * Attempts to get a list of matches that the specified argument could be
     *
     * @param arg
     *         the argument to get matches for
     * @param possible
     *         the {@link String[]} of possible matches
     *
     * @return a list of matches found
     */
    public static List<String> matchTo(String arg, String[] possible) {
        ArrayList<String> matches = new ArrayList<String>();

        for (int index = 0; index < possible.length; index++) {
            if (startsWith(arg, possible[index])) {
                matches.add(possible[index]);
            }
        }

        return matches;
    }

    /**
     * Matches the last argument of specified arguments to the possible matches
     *
     * @param args
     *         the arguments to grab the last argument of
     * @param possible
     *         the {@link String[]} of possible matches
     *
     * @return a list of matches found
     */
    public static List<String> matchTo(String[] args, String[] possible) {
        String lastArg = args[args.length - 1];
        return matchTo(lastArg, possible);
    }

    /**
     * Matches the argument to possible {@link net.canarymod.user.Group} names
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching {@link net.canarymod.user.Group} names found
     */
    public static List<String> matchToGroup(String arg) {
        return matchTo(arg, Canary.usersAndGroups().getGroupNames());
    }

    /**
     * Matches the last argument of specified arguments to possible {@link net.canarymod.user.Group} names
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching {@link net.canarymod.user.Group} names found
     */
    public static List<String> matchToGroup(String[] args) {
        return matchTo(args, Canary.usersAndGroups().getGroupNames());
    }

    /**
     * Matches the argument to possible online {@link net.canarymod.api.entity.living.humanoid.Player} names
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching online {@link net.canarymod.api.entity.living.humanoid.Player} names found
     */
    public static List<String> matchToOnlinePlayer(String arg) {
        return matchTo(arg, Canary.getServer().getPlayerNameList());
    }

    /**
     * Matches the last argument of specified arguments to possible online {@link net.canarymod.api.entity.living.humanoid.Player} names
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching {@link net.canarymod.api.entity.living.humanoid.Player} names found
     */
    public static List<String> matchToOnlinePlayer(String[] args) {
        return matchTo(args, Canary.getServer().getPlayerNameList());
    }

    /**
     * Matches the argument to possible loaded {@link net.canarymod.api.world.World} names
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching loaded {@link net.canarymod.api.world.World} names found
     */
    public static List<String> matchToLoadedWorld(String arg) {
        return matchTo(arg, Canary.getServer().getWorldManager().getLoadedWorldsNames());
    }

    /**
     * Matches the last argument of specified arguments to possible loaded {@link net.canarymod.api.world.World} names
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching loaded {@link net.canarymod.api.world.World} names found
     */
    public static List<String> matchToLoadedWorld(String[] args) {
        return matchTo(args, Canary.getServer().getWorldManager().getLoadedWorldsNames());
    }

    /**
     * Matches the argument to possible loaded {@link net.canarymod.api.world.World} names of specified {@link net.canarymod.api.world.DimensionType}
     *
     * @param arg
     *         the argument to get matches for
     * @param dimensionType
     *         the {@link net.canarymod.api.world.DimensionType} of the {@link net.canarymod.api.world.World}
     *
     * @return list of matching loaded {@link net.canarymod.api.world.World} names found
     */
    public static List<String> matchToLoadedWorld(String arg, DimensionType dimensionType) {
        return matchTo(arg, Canary.getServer().getWorldManager().getLoadedWorldsNamesOfDimension(dimensionType));
    }

    /**
     * Matches the last argument of specified arguments to possible loaded {@link net.canarymod.api.world.World} names of specified {@link net.canarymod.api.world.DimensionType}
     *
     * @param args
     *         the arguments to grab the last argument of
     * @param dimensionType
     *         the {@link net.canarymod.api.world.DimensionType} of the {@link net.canarymod.api.world.World}
     *
     * @return a list of matching loaded {@link net.canarymod.api.world.World} names found
     */
    public static List<String> matchToLoadedWorldOfDimension(String[] args, DimensionType dimensionType) {
        return matchTo(args, Canary.getServer().getWorldManager().getLoadedWorldsNamesOfDimension(dimensionType));
    }

    /**
     * Matches the argument to possible {@link net.canarymod.api.world.DimensionType} names of specified {@link net.canarymod.api.world.DimensionType}
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching {@link net.canarymod.api.world.DimensionType} names found
     */
    public static List<String> matchToDimension(String arg) {
        return matchTo(arg, DimensionType.knownDimensionNames());
    }

    /**
     * Matches the last argument of specified arguments to possible {@link net.canarymod.api.world.DimensionType} names
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching {@link net.canarymod.api.world.DimensionType} names found
     */
    public static List<String> matchToDimension(String[] args) {
        return matchTo(args, DimensionType.knownDimensionNames());
    }
}
