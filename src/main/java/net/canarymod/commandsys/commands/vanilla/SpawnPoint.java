package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.commandsys.CanaryCommandPermissions.SPAWNPOINT$OTHER;

/**
 * SetSpawnPoint command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SpawnPoint extends VanillaCommandWrapper {
    // spawnpoint [player [<x> <y> <z>]]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length > 0) {
            if (isNotSelfOrServer(caller, parameters[0]) && !caller.hasPermission(SPAWNPOINT$OTHER)) {
                caller.notice(Translator.nativeTranslate("commands.generic.permission"));
                return;
            }
        }
        passOn(caller, "spawnpoint", parameters);
    }
}
