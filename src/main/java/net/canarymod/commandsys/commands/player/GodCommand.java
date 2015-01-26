package net.canarymod.commandsys.commands.player;

import net.canarymod.Canary;
import net.canarymod.api.GameMode;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Translator.sendTranslatedNotice;
import static net.canarymod.commandsys.CanaryCommandPermissions.GOD;
import static net.canarymod.commandsys.CanaryCommandPermissions.GOD$OTHER;

/**
 * @author Somners
 */
public class GodCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        if (caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            Player player = caller.asPlayer();
            if (args.length == 0) {
                if (!player.hasPermission(GOD)) {
                    sendTranslatedNotice(caller, "god failed");
                }
                else if (player.getMode() == GameMode.CREATIVE) {
                    sendTranslatedNotice(caller, "god creative");
                }
                else if (player.getCapabilities().isInvulnerable()) {
                    player.getCapabilities().setInvulnerable(false);
                    sendTranslatedNotice(caller, "god disabled");
                }
                else {
                    player.getCapabilities().setInvulnerable(true);
                    sendTranslatedNotice(caller, "god enabled");
                }
                return;
            }
            else {
                if (!player.hasPermission(GOD$OTHER)) {
                    sendTranslatedNotice(caller, "god failed");
                    return;
                }
                // proceed to doAction
            }
        }
        else {
            if (!caller.hasPermission(GOD$OTHER)) {
                sendTranslatedNotice(caller, "god failed");
                return;
            }
            // proceed to doAction
        }
        doAction(args[0], caller);
    }

    private void doAction(String pattern, MessageReceiver caller) {
        Player[] others = Canary.playerSelector().matchPlayers(caller, pattern);
        if (others == null) {
            Player other = Canary.getServer().getPlayer(pattern);
            if (other == null) {
                sendTranslatedNotice(caller, "god failed");
                sendTranslatedNotice(caller, "unknown player", pattern);
                return;
            }
            others = new Player[]{ other };
        }

        for (Player player : others) {
            if (player == null) {
                sendTranslatedNotice(caller, "unknown player", pattern);
                return;
            }
            if (player.getMode() == GameMode.CREATIVE) {
                sendTranslatedNotice(caller, "god creative other", player.getName());
                return;
            }
            if (player.getCapabilities().isInvulnerable()) {
                player.getCapabilities().setInvulnerable(false);
                sendTranslatedNotice(caller, "god disabled other", player.getName());
                sendTranslatedNotice(player, "god disabled");
            }
            else {
                player.getCapabilities().setInvulnerable(true);
                sendTranslatedNotice(caller, "god enabled other", player.getName());
                sendTranslatedNotice(player, "god enabled");
            }
        }
    }
}
