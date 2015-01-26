package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.permissionsystem.PermissionNode;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to check if a player has a permission node
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionCheck extends PlayermodBase {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        PermissionNode node = PermissionNode.fromString(args[1]);
        boolean result;
        boolean hasPath;

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                hasPath = target.getPermissionProvider().pathExists(node.getName());
                result = target.hasPermission(node.getName());

                if (hasPath) {
                    if (result) {
                        caller.message(ChatFormat.YELLOW + target.getName() + ": " + ChatFormat.GREEN + node.getName() + ": true");
                    }
                    else {
                        caller.message(ChatFormat.YELLOW + target.getName() + ": " + ChatFormat.RED + node.getName() + ": false");
                    }
                }
                else {
                    if (result) {
                        caller.message(ChatFormat.YELLOW + target.getName() + ": " + ChatFormat.GREEN + node.getName() + ": true");
                    }
                    else {
                        caller.message(ChatFormat.YELLOW + target.getName() + ": " + ChatFormat.YELLOW + node.getName() + ": " + Translator.translate("no"));
                    }
                }
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
            if (target == null) {
                sendTranslatedNotice(caller, "unknown player", args[0]);
                return;
            }

            hasPath = target.getPermissionProvider().pathExists(node.getName());
            result = target.hasPermission(node.getName());

            if (hasPath) {
                if (result) {
                    caller.message(ChatFormat.GREEN + node.getName() + ": true");
                }
                else {
                    caller.message(ChatFormat.RED + node.getName() + ": false");
                }
            }
            else {
                if (result) {
                    caller.message(ChatFormat.GREEN + node.getName() + ": true");
                }
                else {
                    caller.message(ChatFormat.YELLOW + node.getName() + ": " + Translator.translate("no"));
                }
            }
        }
    }
}
