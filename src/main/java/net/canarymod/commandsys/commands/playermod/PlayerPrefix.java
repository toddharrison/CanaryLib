package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to change the prefix of a player
 *
 * @author Chris (damagefilter)
 */
public class PlayerPrefix implements NativeCommand {
    // player) prefix <player> [prefix]
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod prefix");
            return;
        }
        String prefix = null;
        if (args.length >= 2) {
            prefix = StringUtils.joinString(args, " ", 1).replaceAll("&([A-FK-Oa-fk-oRr0-9])", "\u00A7$1");
        }
        PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
        if (target == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[0]));
            return;
        }
        target.setPrefix(prefix);
        caller.message(Colors.YELLOW + Translator.translate("modify prefix set"));
    }
}
