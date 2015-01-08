package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.commandsys.CanaryCommandPermissions.KILL$OTHER;

/**
 * Kill command wrapper
 *
 * @author Almog (Swift)
 */
public final class Kill extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length > 0 && isNotSelfOrServer(caller, parameters[0]) && !caller.hasPermission(KILL$OTHER)) {
            caller.notice(Translator.nativeTranslate("commands.generic.permission"));
            return;
        }
        passOn(caller, "kill", parameters);
    }
}
