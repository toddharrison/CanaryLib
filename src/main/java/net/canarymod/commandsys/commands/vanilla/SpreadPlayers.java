package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * SpreadPlayers command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SpreadPlayers extends VanillaCommandWrapper {
    // spreadplayers <x> <z> <spreadDistance> <maxRange> <respectTeams true|false> <player ...>

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "spreadplayers", parameters);
    }
}
