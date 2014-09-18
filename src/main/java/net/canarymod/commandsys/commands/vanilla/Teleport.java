package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

/**
 * Teleport command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Teleport extends VanillaCommandWrapper {
    // /tp [target player] <destination player | <<x> <y> <z>>>

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        /* INSERT MAGIC HERE */
        if (!parameters[0].matches("\\d+") && isNotSelfOrServer(caller, parameters[0])) { // if coordinates, server, or self - allow it
            // Ok so we know its a target player or destination player, move to next check
            if (parameters.length > 1 && !parameters[1].matches("\\d+")) { // if digit - allow it
                // We know there is a target player specified
                if (!caller.hasPermission("canary.command.teleport.other")) {
                    caller.notice(Translator.nativeTranslate("commands.generic.permission"));
                    return;
                }
            }
        }
        passOn(caller, "tp", parameters);
    }
}
