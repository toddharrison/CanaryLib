package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.api.world.WorldManager;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * World List Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class ListWorldsCommand implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message(ChatFormat.YELLOW.concat("*** Available Worlds ***"));
        caller.message(ChatFormat.GREEN.concat("(LOADED) ".concat(ChatFormat.RED.toString()).concat("(NOT LOADED)")));
        caller.message(getWorldList());
    }

    private String getWorldList() {
        WorldManager manager = Canary.getServer().getWorldManager();
        StringBuilder builder = new StringBuilder();
        for (String worldName : manager.getExistingWorlds()) {
            if (manager.worldIsLoaded(worldName)) {
                builder.append(ChatFormat.GREEN);
            }
            else {
                builder.append(ChatFormat.RED);
            }
            builder.append(worldName).append(", ");
        }
        return builder.toString().substring(0, builder.length() - 2); // Remove last comma
    }
}
