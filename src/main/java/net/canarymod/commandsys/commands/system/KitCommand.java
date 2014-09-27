package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.kit.Kit;
import net.canarymod.user.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.canarymod.commandsys.CanaryCommandPermissions.*;

/**
 * Kit Command
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class KitCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            server(caller, parameters);
        }
    }

    // Lets give Console ability to give out kits as well (Mainly for online
    // store stuff, yeah! darkdiplomat is thinking!)
    // You're a wise old monk. In the spirit of your wise-ness I shall add this
    // ability to CommandBlocks - Chris
    private void server(MessageReceiver caller, String[] args) {
        //
        // GIVE KITS
        //
        if (args[1].equalsIgnoreCase("give")) {
            // Give kit to a subject
            if (args.length >= 3) {
                boolean override = args.length > 3 && args[3].toLowerCase().equals("override");
                Player recipient = Canary.getServer().matchPlayer(args[2]);

                if (recipient != null) {
                    Kit kit = Canary.kits().getKit(args[1]);

                    if (kit != null) {
                        if (kit.giveKit(recipient, override)) {
                            recipient.message(Colors.YELLOW + Translator.translateAndFormat("kit given other", caller.getName()));
                            return;
                        }
                        else {
                            caller.notice(Translator.translateAndFormat("kit unavailable other", recipient.getName()));
                            return;
                        }
                    }
                    else {
                        caller.notice(Translator.translateAndFormat("kit invalid", args[1]));
                        return;
                    }
                }
                else {
                    caller.notice(Translator.translateAndFormat("unknown player", args[2]));
                    return;
                }
            }
        }
        else if (args[0].equalsIgnoreCase("list")) {
            caller.message(Colors.YELLOW + "Available Kits: ");
            List<Kit> kits = Canary.kits().getAllKits();
            StringBuilder kitList = new StringBuilder();

            for (Kit k : kits) {
                kitList.append(k.getName()).append(",");
            }
            caller.message(kitList.toString());
            return;
        }
        caller.notice(Translator.translateAndFormat("usage", "/kit give <name> <player> [override]"));
        caller.notice(Translator.translateAndFormat("usage", "/kit list"));
    }

    private void player(Player player, String[] args) {
        //
        // LIST KITS
        //
        if (args[0].equalsIgnoreCase("list")) {
            player.message(Colors.YELLOW + "Available Kits: ");
            List<Kit> kits = Canary.kits().getAllKits();
            StringBuilder kitList = new StringBuilder();

            for (Kit k : kits) {
                List<String> groups = new ArrayList<String>();
                for (String s : k.getGroups()) {
                    groups.add(s);
                }
                if (groups.contains(player.getGroup().getName()) || player.isAdmin()) {
                    kitList.append(k.getName()).append(",");
                }
            }
            player.message(kitList.toString());
            return;
        }
        //
        // GIVE KITS
        //
        if (args[0].equalsIgnoreCase("give")) {

            // Give kit to player
            if (args.length == 2) {
                Kit kit = Canary.kits().getKit(args[1]);

                if (kit != null) {
                    if (kit.giveKit(player, false)) {
                        player.message(Colors.YELLOW
                                + Translator.translate("kit given"));
                        return;
                    }
                    else {
                        player.notice(Translator.translate("kit unavailable"));
                        return;
                    }
                }
                else {
                    player.notice(Translator.translateAndFormat("kit invalid", args[1]));
                    return;
                }
            }

            // Give kit to a subject
            if (args.length > 2) {
                if (!player.hasPermission(KIT$OTHER)) {
                    return;
                }
                Player recipient = Canary.getServer().matchPlayer(args[2]);

                if (recipient != null) {
                    Kit kit = Canary.kits().getKit(args[1]);

                    if (kit != null) {
                        if (kit.giveKit(recipient, false)) {
                            recipient
                                    .message(Colors.YELLOW
                                            + Translator.translateAndFormat(
                                            "kit given other",
                                            player.getName()));
                            return;
                        }
                        else {
                            player.notice(Translator.translateAndFormat(
                                    "kit unavailable other",
                                    recipient.getName()));
                            return;
                        }
                    }
                    else {
                        player.notice(Translator.translateAndFormat(
                                "kit invalid", args[1]));
                        return;
                    }
                }
                else {
                    player.notice(Translator.translateAndFormat(
                            "unknown player", args[1]));
                    return;
                }
            }
        }

        //
        // CREATE KITS
        //
        if (args[0].equalsIgnoreCase("create")) {
            if (args.length < 3) {
                player.notice(Translator.translateAndFormat("usage",
                        "/kit create <name> <use delay> [G|P Groups|Players]")
                        + " - " + Translator.translate("kit from inventory"));
                return;
            }
            // Default public kit
            if (args.length == 3) {
                Kit newKit = new Kit();

                newKit.setContent(new ArrayList<Item>(Arrays.asList(player
                        .getInventory().getContents())));
                newKit.setDelay(Integer.parseInt(args[2]));
                newKit.setName(args[1]);
                Canary.kits().addKit(newKit);
                player.message(Colors.YELLOW
                        + Translator.translateAndFormat("kit created", args[1]));
                return;
            }

            if (args.length >= 5) {
                // ADD GROUPS KIT
                if (args[3].equalsIgnoreCase("G")
                        && player
                        .hasPermission(KIT$GROUP)) {
                    String[] groups = new String[args.length - 4];

                    for (int i = 0; i < groups.length; i++) {
                        Group g = Canary.usersAndGroups().getGroup(args[i + 4]);

                        if (g != null) {
                            groups[i] = g.getName();
                        }
                        else {
                            groups[i] = Canary.usersAndGroups()
                                    .getDefaultGroup().getName();
                        }
                    }
                    Kit newKit = new Kit();

                    newKit.setContent(new ArrayList<Item>(Arrays.asList(player
                            .getInventory().getContents())));
                    newKit.setDelay(Integer.parseInt(args[2]));
                    newKit.setName(args[1]);
                    newKit.setGroups(groups);
                    Canary.kits().addKit(newKit);
                    player.message(Colors.YELLOW
                            + Translator.translateAndFormat(
                            "kit created group", args[1]));
                    return;
                } // ADD PLAYER PRIVATE KIT
                else if (args[3].equalsIgnoreCase("G")
                        && player
                        .hasPermission(KIT$PRIVATE)) {
                    String[] players = new String[args.length - 4];

                    System.arraycopy(args, 4, players, 0, players.length);
                    Kit newKit = new Kit();

                    newKit.setContent(new ArrayList<Item>(Arrays.asList(player
                            .getInventory().getContents())));
                    newKit.setDelay(Integer.parseInt(args[2]));
                    newKit.setName(args[1]);
                    newKit.setOwner(players);
                    Canary.kits().addKit(newKit);
                    player.message(Colors.YELLOW + Translator.translateAndFormat("kit created private", args[1]));
                    return;
                }
                else {
                    player.notice(Translator.translateAndFormat("usage", "/kit create <name> <use delay> [G|P Groups|Players]")
                            + " - " + Translator.translate("kit from inventory"));
                    return;
                }
            }
            player.notice(Translator.translateAndFormat("usage",
                    "/kit create <name> <use delay> [G|P Groups|Players]")
                    + " - " + Translator.translate("kit from inventory"));
            return;

        }
        player.notice(Translator.translateAndFormat("usage",
                "/kit give <name> [player]"));
        player.notice(Translator.translateAndFormat("usage",
                "/kit create <name> <use delay> [G|P Groups|Players]")
                + " - "
                + Translator.translate("kit from inventory"));
    }
}
