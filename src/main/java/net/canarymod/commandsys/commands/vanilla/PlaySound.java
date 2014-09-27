package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.commandsys.CanaryCommandPermissions.PLAYSOUND$OTHER;

/**
 * PlaySound command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class PlaySound extends VanillaCommandWrapper {
    // playsound <sound> <player> [x] [y] [z] [volume] [pitch] [minimumVolume]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (isNotSelfOrServer(caller, parameters[1]) && !caller.hasPermission(PLAYSOUND$OTHER)) {
            caller.notice(Translator.nativeTranslate("commands.generic.permission"));
            return;
        }
        passOn(caller, "playsound", parameters);
    }
}
