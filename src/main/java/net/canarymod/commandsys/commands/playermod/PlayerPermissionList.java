package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to list all permissions a player has
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionList implements NativeCommand {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        PlayerReference player = Canary.getServer().matchKnownPlayer(args[0]);
        if (player == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[0]));
            return;
        }
        player.getPermissionProvider().printPermissionsToCaller(caller);
    }
}
