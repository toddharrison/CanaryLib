package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.commandsys.CanaryCommandPermissions.HOME$OTHER;


/**
 * Command to teleport you to your own or another player home
 *
 * @author Chris (damagefilter)
 */
public class Home implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console(caller);
        }
        else if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            others(caller, parameters);
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("home console"));
    }

    // Special behaviour for command blocks, they teleport the given player to their homes
    private void others(MessageReceiver player, String[] args) {
        if (args.length < 1) {
            Canary.help().getHelp(player, "home");
            return;
        }
        if (player.hasPermission(HOME$OTHER)) {
            Player[] others = Canary.playerSelector().matchPlayers(player, args[0]);
            if (others != null) {
                for (Player other : others) {
                    if (other != null) {
                        if (other.hasHome()) {
                            other.teleportTo(other.getHome());
                        }
                        else {
                            player.notice(Translator.translateAndFormat("no home set other", new Object[]{other.getName()}));
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
                    player.notice(Translator.translateAndFormat("no home set other", new Object[]{target.getName()}));
                }
            }
        }
    }

    private void player(Player player, String[] args) {
        if (args == null || args.length == 0) {
            if (player.hasHome()) {
                player.notice(Translator.translate("home teleport"));
                player.teleportTo(player.getHome());
            }
            else {
                player.notice(Translator.translate("no home set"));
            }
        }
        else {
            others((MessageReceiver) player, args);
        }
    }

}
