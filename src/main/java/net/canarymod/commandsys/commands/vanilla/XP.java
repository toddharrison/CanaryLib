package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.commandsys.CanaryCommandPermissions.XP$OTHER;

/**
 * XP command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class XP extends VanillaCommandWrapper {
    // xp <amount[L]> [player]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length > 1) {
            if (isNotSelfOrServer(caller, parameters[0]) && !caller.hasPermission(XP$OTHER)) {
                caller.notice(Translator.nativeTranslate("commands.generic.permission"));
                return;
            }
        }
        passOn(caller, "xp", parameters);
    }
}
