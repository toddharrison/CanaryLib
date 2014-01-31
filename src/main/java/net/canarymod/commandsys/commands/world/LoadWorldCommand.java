package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.WorldManager;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks for and loads a World
 *
 * @author Jason (darkdiplomat)
 */
public final class LoadWorldCommand implements NativeCommand {
    private final Matcher matcher = Pattern.compile(".+_\\w+").matcher("");

    public final void execute(MessageReceiver caller, String[] parameters) {
        try {
            WorldManager manage = Canary.getServer().getWorldManager();
            String worldName = parameters[1];
            String fqName = parameters[1];
            DimensionType type = null;

            if (matcher.reset(worldName).matches()) {
                String dim = worldName.substring(worldName.lastIndexOf('_') + 1);
                worldName = worldName.substring(0, worldName.lastIndexOf('_'));
                type = DimensionType.fromName(dim);
            }
            else if (parameters.length > 2) {
                if (parameters[2].matches("\\d")) {
                    type = DimensionType.fromId(Integer.parseInt(parameters[2]));
                }
                else {
                    type = DimensionType.fromName(parameters[2].toUpperCase());
                }
                fqName = worldName + "_" + type.getName();
            }

            if (type != null) {
                if (manage.worldExists(fqName)) {
                    if (!manage.worldIsLoaded(worldName, type)) {
                        manage.loadWorld(worldName, type);
                        caller.notice("World Loaded!");
                    }
                    else {
                        caller.notice("World was already loaded...");
                    }
                }
                else {
                    caller.notice("World '" + parameters[1] + "' does not exist...");
                }
            }
            else {
                caller.notice("Dimension type is non-existant");
            }
        }
        catch (Exception ex) {
            caller.notice("Failed to load '" + parameters[1] + "'. See console for error.");
        }
    }
}
