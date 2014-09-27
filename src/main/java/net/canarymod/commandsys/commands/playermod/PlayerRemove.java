package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to remove player data from the database
 *
 * @author Chris (damagefilter)
 */
public class PlayerRemove implements NativeCommand {
    // player) remove <playername>
    @Override
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod remove");
            return;
        }
        PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
        if (target == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[0]));
            return;
        }
        target.setGroup(Canary.usersAndGroups().getDefaultGroup());
        Canary.usersAndGroups().removeUserData(target.getUUIDString());
        caller.notice(Translator.translate("modify player removed"));
    }
}
