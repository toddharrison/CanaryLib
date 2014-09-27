package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.permissionsystem.PermissionNode;

/**
 * Command to add a permission node to a player
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionAdd implements NativeCommand {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        PlayerReference player = Canary.getServer().matchKnownPlayer(args[0]);
        PermissionNode node = PermissionNode.fromString(args[1]);
        if (player == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[0]));
            return;
        }
        else {
            player.getPermissionProvider().addPermission(node.getName(), node.getValue());
        }
        caller.message(Colors.YELLOW + Translator.translate("modify permission added"));
    }
}
