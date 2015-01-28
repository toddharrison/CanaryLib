package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Translator.sendTranslatedNotice;
import static net.canarymod.commandsys.CanaryCommandPermissions.HOME$OTHER;

/**
 * Command to teleport you to your own or another player home
 *
 * @author Chris (damagefilter)
 */
public class Home implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            player(caller.asPlayer(), parameters);
        }
        else {
            others(caller, parameters);
        }
    }

    // Special behaviour for command blocks, they teleport the given player to their homes
    private void others(MessageReceiver caller, String[] args) {
        if (args.length < 1) {
            Canary.help().getHelp(caller, "home");
            return;
        }
        if (caller.hasPermission(HOME$OTHER)) {
            Player[] others = Canary.playerSelector().matchPlayers(caller, args[0]);
            if (others != null) {
                for (Player other : others) {
                    if (other != null) {
                        if (other.hasHome()) {
                            other.teleportTo(other.getHome());
                        }
                        else {
                            sendTranslatedNotice(caller, "no home set other", other.getName());
                        }
                    }
                }
                return;
            }
            Player target = Canary.getServer().matchPlayer(args[0]);
            if (target != null) {
                if (target.hasHome()) {
                    target.teleportTo(target.getHome());
                }
                else {
                    sendTranslatedNotice(caller, "no home set other", target.getName());
                }
            }
        }
    }

    private void player(Player player, String[] args) {
        if (args == null || args.length == 0) {
            if (player.hasHome()) {
                sendTranslatedNotice(player, "home teleport");
                player.teleportTo(player.getHome());
            }
            else {
                sendTranslatedNotice(player, "no home set");
            }
        }
        else {
            others(player, args);
        }
    }
}
