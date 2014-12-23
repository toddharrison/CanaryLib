package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.permissionsystem.PermissionNode;

/**
 * Command to check if a player has a permission node
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionCheck implements NativeCommand {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        PlayerReference player = Canary.getServer().matchKnownPlayer(args[0]);
        PermissionNode node = PermissionNode.fromString(args[1]);
        boolean result;
        boolean hasPath;
        if (player == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[0]));
            return;
        }
        else {
            result = player.hasPermission(node.getName());
            hasPath = player.getPermissionProvider().pathExists(node.getName());
        }
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
