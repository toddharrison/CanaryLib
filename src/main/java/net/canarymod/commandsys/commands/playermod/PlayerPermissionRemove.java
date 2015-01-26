package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.permissionsystem.PermissionNode;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to remove a permission node from a player
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionRemove extends PlayermodBase {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        PlayerReference player = Canary.getServer().matchKnownPlayer(args[0]);
        PermissionNode node = PermissionNode.fromString(args[1]);
        if (player == null) {
            sendTranslatedNotice(caller, "unknown player", args[0]);
            return;
        }
        else {
            Canary.permissionManager().removePlayerPermission(node.getName(), player);
        }
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "modify permission removed");
    }
}
