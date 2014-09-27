package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.commandsys.CanaryCommandPermissions.ACHIEVEMENT$OTHER;

/**
 * Achievement command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Achievement extends VanillaCommandWrapper {
    // achievement give <stat_name> [player]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length > 2) {
            if (isNotSelfOrServer(caller, parameters[0]) && !caller.hasPermission(ACHIEVEMENT$OTHER)) {
                caller.notice(Translator.nativeTranslate("commands.generic.permission"));
                return;
            }
        }
        passOn(caller, "achievement", parameters);
    }
}
