package net.canarymod.commandsys;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.WorldType;
import net.canarymod.bansystem.Ban;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.kit.Kit;
import net.canarymod.warp.Warp;

import java.lang.reflect.Method;
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
     * @return a list of matching online {@link net.canarymod.api.entity.living.humanoid.Player} names found
     */
    public static List<String> matchToOnlinePlayer(String[] args) {
        return matchTo(args, Canary.getServer().getPlayerNameList());
    }

    /**
     * Matches the argument to possible {@link net.canarymod.api.entity.living.humanoid.Player} names, including offline players
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching {@link net.canarymod.api.entity.living.humanoid.Player} names found
     */
    public static List<String> matchToKnownPlayer(String arg) {
        return matchTo(arg, Canary.getServer().getKnownPlayerNames());
    }

    /**
     * Matches the last argument of specified arguments to possible {@link net.canarymod.api.entity.living.humanoid.Player} names, including offline players
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching {@link net.canarymod.api.entity.living.humanoid.Player} names found
     */
    public static List<String> matchToKnownPlayer(String[] args) {
        return matchTo(args, Canary.getServer().getKnownPlayerNames());
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

    /**
     * Matches the last argument of specified arguments to possible existing {@link net.canarymod.api.world.World} names
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching existing {@link net.canarymod.api.world.World} names
     */
    public static List<String> matchToKnownWorld(String[] args) {
        return matchTo(args, Canary.getServer().getWorldManager().getExistingWorldsArray());
    }

    /**
     * Matches the argument to possible existing {@link net.canarymod.api.world.World} names
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching existing {@link net.canarymod.api.world.World} names found
     */
    public static List<String> matchToKnownWorld(String arg) {
        return matchTo(arg, Canary.getServer().getWorldManager().getExistingWorldsArray());
    }

    /**
     * Matches the last argument of specified arguments to possible existing {@link net.canarymod.api.world.WorldType} names
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching existing {@link net.canarymod.api.world.WorldType} names
     */
    public static List<String> matchToWorldType(String[] args) {
        return matchTo(args, WorldType.getTypeNames());
    }

    /**
     * Matches the argument to possible existing {@link net.canarymod.api.world.WorldType} names
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching existing {@link net.canarymod.api.world.WorldType} names found
     */
    public static List<String> matchToWorldType(String arg) {
        return matchTo(arg, WorldType.getTypeNames());
    }

    /**
     * Matches the argument to possible banned subjects
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching banned subjects found
     */
    public static List<String> matchToBannedSubject(String arg) {
        ArrayList<String> banned = new ArrayList<String>();
        for (Ban ban : Canary.bans().getAllBans()) {
            banned.add(ban.getSubject());
        }
        return matchTo(arg, banned.toArray(new String[banned.size()]));
    }

    /**
     * Matches the last argument of specified arguments to possible banned subjects
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching banned subjects found
     */
    public static List<String> matchToBannedSubject(String[] args) {
        ArrayList<String> banned = new ArrayList<String>();
        for (Ban ban : Canary.bans().getAllBans()) {
            banned.add(ban.getSubject());
        }
        return matchTo(args, banned.toArray(new String[banned.size()]));
    }

    /**
     * Matches the argument to possible {@link net.canarymod.kit.Kit} names
     *
     * @param arg
     *         the argument to get matches for
     * @param caller
     *         the {@link net.canarymod.chat.MessageReceiver} to use for giving checks
     *
     * @return list of matching {@link net.canarymod.kit.Kit} names found
     */
    public static List<String> matchToKitNames(String arg, MessageReceiver caller) {
        Player subject = caller instanceof Player ? (Player) caller : null;
        ArrayList<String> kitNames = new ArrayList<String>();
        for (Kit kit : Canary.kits().getAllKits()) {
            if (subject != null && !kit.canBeGiven(subject)) {
                continue;
            }
            kitNames.add(kit.getName());
        }
        return matchTo(arg, kitNames.toArray(new String[kitNames.size()]));
    }

    /**
     * Matches the last argument of specified arguments to possible {@link net.canarymod.kit.Kit} names
     *
     * @param args
     *         the arguments to grab the last argument of
     * @param caller
     *         the {@link net.canarymod.chat.MessageReceiver} to use for giving checks
     *
     * @return a list of matching {@link net.canarymod.kit.Kit} names found
     */
    public static List<String> matchToKitNames(String[] args, MessageReceiver caller) {
        Player subject = caller instanceof Player ? (Player) caller : null;
        ArrayList<String> kitNames = new ArrayList<String>();
        for (Kit kit : Canary.kits().getAllKits()) {
            if (subject != null && !kit.canBeGiven(subject)) {
                continue;
            }
            kitNames.add(kit.getName());
        }
        return matchTo(args, kitNames.toArray(new String[kitNames.size()]));
    }

    /**
     * Matches the argument to possible {@link net.canarymod.warp.Warp} names
     *
     * @param arg
     *         the argument to get matches for
     * @param caller
     *         the {@link net.canarymod.chat.MessageReceiver} to use for permission checks
     *
     * @return list of matching {@link net.canarymod.warp.Warp} names found
     */
    public static List<String> matchToWarpNames(String arg, MessageReceiver caller) {
        Player subject = caller instanceof Player ? (Player) caller : null;
        ArrayList<String> warpNames = new ArrayList<String>();
        for (Warp warp : Canary.warps().getAllWarps()) {
            if (warp.isPlayerHome()) {
                continue;
            }
            if (subject != null && !warp.canWarp(subject)) {
                continue;
            }
            warpNames.add(warp.getName());
        }
        return matchTo(arg, warpNames.toArray(new String[warpNames.size()]));
    }

    /**
     * Matches the last argument of specified arguments to possible {@link net.canarymod.warp.Warp} names
     *
     * @param args
     *         the arguments to grab the last argument of
     * @param caller
     *         the {@link net.canarymod.chat.MessageReceiver} to use for giving checks
     *
     * @return a list of matching {@link net.canarymod.warp.Warp} names found
     */
    public static List<String> matchToWarpNames(String[] args, MessageReceiver caller) {
        Player subject = caller instanceof Player ? (Player) caller : null;
        ArrayList<String> warpNames = new ArrayList<String>();
        for (Warp warp : Canary.warps().getAllWarps()) {
            if (warp.isPlayerHome()) {
                continue;
            }
            if (subject != null && !warp.canWarp(subject)) {
                continue;
            }
            warpNames.add(warp.getName());
        }
        return matchTo(args, warpNames.toArray(new String[warpNames.size()]));
    }

    /**
     * Matches the argument to possible existing {@link net.canarymod.api.inventory.ItemType} machine names
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching existing {@link net.canarymod.api.inventory.ItemType} machine names
     */
    public static List<String> matchToItemType(String arg) {
        return matchTo(arg, itemTypeNames(false));
    }

    /**
     * Matches the last argument of specified arguments to possible existing {@link net.canarymod.api.inventory.ItemType} machine names
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching existing {@link net.canarymod.api.inventory.ItemType} machine names
     */
    public static List<String> matchToItemType(String[] args) {
        return matchTo(args, itemTypeNames(false));
    }

    /**
     * Matches the argument to possible existing {@link net.canarymod.api.inventory.ItemType} machine names with data value
     *
     * @param arg
     *         the argument to get matches for
     *
     * @return list of matching existing {@link net.canarymod.api.inventory.ItemType} machine names with data value
     */
    public static List<String> matchToItemTypeAndData(String arg) {
        return matchTo(arg, itemTypeNames(true));
    }

    /**
     * Matches the last argument of specified arguments to possible existing {@link net.canarymod.api.inventory.ItemType} machine names with data value
     *
     * @param args
     *         the arguments to grab the last argument of
     *
     * @return a list of matching existing {@link net.canarymod.api.inventory.ItemType} machine names with data value
     */
    public static List<String> matchToItemTypeAndData(String[] args) {
        return matchTo(args, itemTypeNames(true));
    }

    /**
     * Generates a new TabCompleteDispatch for a command.
     * Returns null if nothing suitable was found within the given CommandListener
     *
     * @param listener
     *         the listener to scan
     * @param aliases
     *         the aliases of ONE SINGLE COMMAND that apply for this dispatcher
     *
     * @return new dispatcher on success or null on failure
     */
    @SuppressWarnings("unchecked")
    public static TabCompleteDispatch findDispatcherFor(final CommandListener listener, String[] aliases, String parent) {
        String full = parent != null ? parent + " %s" : "%s";
        Method[] methods = listener.getClass().getDeclaredMethods();
        for (final Method method : methods) {
            if (!method.isAnnotationPresent(TabComplete.class)) {
                continue;
            }
            if (!List.class.isAssignableFrom(method.getReturnType())) {
                Canary.log.error("Failed to get a tab complete dispatcher for " + aliases[0] + ". It does not return a list! Checking next ...");
                continue; // Keep looking
            }
            TabComplete tabInfo = method.getAnnotation(TabComplete.class);
            for (String alias : aliases) {
                if (ToolBox.arrayContains(tabInfo.commands(), String.format(full, alias).replaceAll("\\.", " "))) {
                    return new TabCompleteDispatch() {
                        @Override
                        public List<String> complete(MessageReceiver msgrec, String[] args) throws TabCompleteException {
                            try {
                                return (List<String>) method.invoke(listener, msgrec, args);
                            }
                            catch (Exception e) {
                                throw new TabCompleteException("Failed to execute tab completion ...", e);
                            }
                        }
                    };
                }
            }
        }
        return null;
    }

    private static String[] itemTypeNames(boolean appendData) {
        ItemType[] types = ItemType.values();
        ArrayList<String> names = new ArrayList<String>();
        for (ItemType type : types) {
            String name = type.getMachineName();
            if (!appendData) {
                names.add(name);
            }
            else if (!names.contains(name + ":" + type.getData())) {
                names.add(name + ":" + type.getData());
            }
        }
        return names.toArray(new String[names.size()]);
    }
}
