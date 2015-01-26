package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.permissionsystem.PermissionNode;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to add a permission node to a player
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionAdd extends PlayermodBase {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        PermissionNode node = PermissionNode.fromString(args[1]);

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                target.getPermissionProvider().addPermission(node.getName(), node.getValue());
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
            if (target == null) {
                sendTranslatedNotice(caller, "unknown player", args[0]);
                return;
            }
            else {
                target.getPermissionProvider().addPermission(node.getName(), node.getValue());
            }
        }
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "modify permission added");
    }
}
