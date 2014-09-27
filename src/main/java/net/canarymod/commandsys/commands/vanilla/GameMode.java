package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.commandsys.CanaryCommandPermissions.GAMEMODE$OTHER;

/**
 * GameMode command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class GameMode extends VanillaCommandWrapper {
    // gamemode <player> <mode> | alias mode

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (isNotSelfOrServer(caller, parameters[0]) && !caller.hasPermission(GAMEMODE$OTHER)) {
            caller.notice(Translator.nativeTranslate("commands.generic.permission"));
            return;
        }
        passOn(caller, "gamemode", parameters);
    }
}
