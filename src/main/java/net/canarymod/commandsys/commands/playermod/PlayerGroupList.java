package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to list all the groups a player belongs to
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupList implements NativeCommand {
    // player) group list <player>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod group list");
            return;
        }
        PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);

        if (target == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[0]));
            return;
        }
        for (Group g : target.getPlayerGroups()) {
            caller.message(g.getName() + (g.hasParent() ? " : " + g.getParent().getName() : ""));
        }
    }
}
