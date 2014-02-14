package net.canarymod.commandsys.commands;

import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.CommandBlockLogic;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

public class RestartServer implements NativeCommand {
    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            caller.notice(caller.getName() + " issued a manual restart!");
            if (parameters.length == 2 && parameters[1].equalsIgnoreCase("-all")) {
                ((Server) caller).restart(true);
            } else {
                ((Server) caller).restart(false);
            }
        } else if (caller instanceof Player || caller instanceof CommandBlockLogic) {
            caller.notice("You cannot restart the server from in-game. Please use the console!");
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }
}
