package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.animal.Tameable;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;
import net.canarymod.config.WorldConfiguration;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * World Information command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class WorldInfoCommand implements NativeCommand {
    private final Matcher matcher = Pattern.compile(".+_\\w+").matcher("");
    private final String colorize = ChatFormat.YELLOW.concat("%s: ").concat(ChatFormat.GRAY.concat("%s"));

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        try {
            WorldManager manage = Canary.getServer().getWorldManager();
            String worldName = parameters[0];
            String fqName = parameters[0];
            String dim = null;
            DimensionType type = null;

            if (matcher.reset(worldName).matches()) {
                dim = worldName.substring(worldName.lastIndexOf('_') + 1);
                worldName = worldName.substring(0, worldName.lastIndexOf('_'));
                type = DimensionType.fromName(dim);
            }
            else if (parameters.length > 1) {
                dim = parameters[1].toUpperCase();
                if (parameters[1].matches("\\d")) {
                    type = DimensionType.fromId(Integer.parseInt(dim));
                }
                else {
                    type = DimensionType.fromName(dim);
                }
                fqName = worldName + "_" + type.getName();
            }

            if (type != null) {
                if (manage.worldExists(fqName)) {
                    WorldConfiguration config = Configuration.getWorldConfig(fqName);
                    for (Map.Entry<String, String> cfg : config.getFile().getPropertiesMap().entrySet()) {
                        caller.message(String.format(colorize, cfg.getKey(), cfg.getValue()));
                    }

                    if (manage.worldIsLoaded(worldName, type)) {
                        World world = manage.getWorld(fqName, false);
                        doEntityCount(caller, world);
                        caller.message(String.format(colorize, "Chunks Loaded", world.getLoadedChunks().size()));
                        caller.message(String.format(colorize, "Villages Loaded", world.getVillages().size()));
                        caller.message(String.format(colorize, "Total Time", world.getTotalTime()));
                        caller.message(String.format(colorize, "Raw Time", world.getRawTime()));
                        caller.message(String.format(colorize, "Relative Time", world.getRelativeTime()));
                        caller.message(String.format(colorize, "Spawn", world.getSpawnLocation().toString()));
                        caller.message(String.format(colorize, "Raining", world.isRaining()));
                        caller.message(String.format(colorize, "Thundering", world.isThundering()));
                    }
                    else {
                        sendTranslatedNotice(caller, "world info notloaded");
                    }
                }
                else {
                    sendTranslatedNotice(caller, "unknown world", parameters[0]);
                }
            }
            else {
                sendTranslatedNotice(caller, "unknown dimension", dim);
            }
        }
        catch (Exception ex) {
            caller.notice("Failed to find information for '" + parameters[0] + "'. See console for error.");
            Canary.log.error("Error executing command '/world info' for caller '" + caller.getName() + "'", ex);
        }
    }

    private void doEntityCount(MessageReceiver caller, World world) {
        int mobCount = 0, passivesCount = 0, utilityCount = 0, tameCount = 0;
        for (Entity entity : world.getTrackedEntities()) {
            if (entity.isPlayer()) {
                continue;
            }

            if (entity.isMob()) {
                mobCount++;
            }
            else if (entity.isAnimal()) {
                if (entity instanceof Tameable) {
                    tameCount++;
                }
                passivesCount++;
            }
            else if (entity.isLiving()) {
                passivesCount++;
            }
            else {
                utilityCount++;
            }
        }
        caller.message(String.format(colorize, "Entities Total", world.getTrackedEntities().size()));
        caller.message(String.format(colorize, "  Monsters", mobCount));
        caller.message(String.format(colorize, "  Passives", passivesCount));
        caller.message(String.format(colorize, "    Tame", tameCount));
        caller.message(String.format(colorize, "  Utility", utilityCount));
        caller.message(String.format(colorize, "  Players", world.getPlayerList().size()));
    }
}
