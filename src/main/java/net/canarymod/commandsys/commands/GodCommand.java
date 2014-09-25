package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.GameMode;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/** @author Somners */
public class GodCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if ((caller instanceof Player)) {
            player((Player) caller, parameters);
        } else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if (args.length != 1) {
            Canary.help().getHelp(caller, "god");
            return;
        }
        if (!caller.hasPermission("canary.command.god.other")) {
            caller.notice(Translator.translate("god failed"));
            return;
        }
        doAction(args[0], caller);
    }

    private void player(Player player, String[] args) {
        if (args.length == 1) {
            if (!player.hasPermission("canary.command.god")) {
                player.notice(Translator.translate("god failed"));
                return;
            }
            if (player.getMode() == GameMode.CREATIVE) {
                player.notice(Translator.translate("god creative"));
                return;
            }
            if (player.getCapabilities().isInvulnerable()) {
                player.getCapabilities().setInvulnerable(false);
                player.notice(Translator.translate("god disabled"));
            }
            else {
                player.getCapabilities().setInvulnerable(true);
                player.notice(Translator.translate("god enabled"));
            }
        }
        else if (args.length == 2) {
            if (!player.hasPermission("canary.command.god.other")) {
                player.notice(Translator.translate("god failed"));
                return;
            }
            doAction(args[1], player);
        }
        else {
            player.notice(Translator.translate("god failed") + " " + Translator.translate("usage"));
            Canary.help().getHelp(player, "god");
        }
    }

    private void doAction(String pattern, MessageReceiver caller) {
        Player[] others = Canary.playerSelector().matchPlayers(caller, pattern);
        if (others != null) {
            for (Player player : others) {
                if (player == null) {
                    caller.notice(Translator.translate("god failed") + " " + Translator.translateAndFormat("unknown player", new Object[]{ pattern }));
                    return;
                }
                if (player.getMode() == GameMode.CREATIVE) {
                    caller.notice(Translator.translateAndFormat("god creative other", new Object[]{ player.getName() }));
                    return;
                }
                if (player.getCapabilities().isInvulnerable()) {
                    player.getCapabilities().setInvulnerable(false);
                    caller.notice(Translator.translateAndFormat("god disabled other", new Object[]{ player.getName() }));
                    player.notice(Translator.translate("god disabled"));
                }
                else {
                    player.getCapabilities().setInvulnerable(true);
                    caller.notice(Translator.translateAndFormat("god enabled other", new Object[]{ player.getName() }));
                    player.notice(Translator.translate("god enabled"));
                }
            }
            return;
        }
        Player other = Canary.getServer().getPlayer(pattern);
        if (other == null) {
            caller.notice(Translator.translate("god failed") + " " + Translator.translateAndFormat("unknown player", new Object[]{ pattern }));
            return;
        }
        if (other.getMode() == GameMode.CREATIVE) {
            caller.notice(Translator.translateAndFormat("god creative other", new Object[]{ other.getName() }));
            return;
        }
        if (other.getCapabilities().isInvulnerable()) {
            other.getCapabilities().setInvulnerable(false);
            caller.notice(Translator.translateAndFormat("god disabled other", new Object[]{ other.getName() }));
            other.notice(Translator.translate("god disabled"));
        }
        else {
            other.getCapabilities().setInvulnerable(true);
            caller.notice(Translator.translateAndFormat("god enabled other", new Object[]{ other.getName() }));
            other.notice(Translator.translate("god enabled"));
        }
    }
}
