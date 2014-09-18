package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

/**
 * Clear Inventory command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Clear extends VanillaCommandWrapper {
    // clear <player> [item] [data]  | alias clearinventory

    @Override
    public final void execute(MessageReceiver caller, String[] parameters) {
        if (isNotSelfOrServer(caller, parameters[0]) && !caller.hasPermission("canary.command.clear.other")) {
            caller.notice(Translator.nativeTranslate("commands.generic.permission"));
            return;
        }
        passOn(caller, "clear", parameters);
    }
}
