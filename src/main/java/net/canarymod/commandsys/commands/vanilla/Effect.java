package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

/**
 * Effect command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Effect extends VanillaCommandWrapper {
    // effect <player> <effect> [seconds] [amplifier]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (isNotSelfOrServer(caller, parameters[0]) && !caller.hasPermission("canary.command.effect.other")) {
            caller.notice(Translator.nativeTranslate("commands.generic.permission"));
            return;
        }
        passOn(caller, "effect", parameters);
    }
}
