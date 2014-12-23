package net.canarymod.commandsys.commands.world;

import net.canarymod.api.MobSpawnerLogic;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.MobSpawner;
import net.canarymod.chat.MessageReceiver;

/**
 * MobSpawner set subcommand
 *
 * @author Jason Jones (darkdiplomat)
 */
public class MobSpawnerSet extends MobSpawnerCommand {

    @Override
    public void execute(MessageReceiver caller, String[] args) {
        if (canExecute(caller)) {
            Player player = (Player)caller;
            MobSpawner mobSpawner = getSpawner(player);
            if (mobSpawner != null) {
                MobSpawnerLogic logic = mobSpawner.getLogic();
                boolean changeMade = false;
                for (String arg : args) {
                    try {
                        // A proper Spawn name has to start with a capital letter...
                        if (arg.matches("((?i)id):[A-Z][a-zA-Z]+")) {
                            logic.setSpawnId(arg.split(":")[1]);
                            changeMade = true;
                        }
                        else if (arg.matches("(?i)delay:\\d+")) {
                            logic.setDelay(Integer.parseInt(arg.split(":")[1]));
                            changeMade = true;
                        }
                        else if (arg.matches("(?i)minDelay:\\d+")) {
                            logic.setMinDelay(Integer.parseInt(arg.split(":")[1]));
                            changeMade = true;
                        }
                        else if (arg.matches("(?i)maxDelay:\\d+")) {
                            logic.setMaxDelay(Integer.parseInt(arg.split(":")[1]));
                            changeMade = true;
                        }
                        else if (arg.matches("(?i)count:\\d+")) {
                            logic.setSpawnCount(Integer.parseInt(arg.split(":")[1]));
                            changeMade = true;
                        }
                        else if (arg.matches("(?i)maxNearby:\\d+")) {
                            logic.setMaxNearbyEntities(Integer.parseInt(arg.split(":")[1]));
                            changeMade = true;
                        }
                        else if (arg.matches("(?i)playerRange:\\d+")) {
                            logic.setRequiredPlayerRange(Integer.parseInt(arg.split(":")[1]));
                            changeMade = true;
                        }
                        else if (arg.matches("(?i)spawnRange:\\d+")) {
                            logic.setSpawnRange(Integer.parseInt(arg.split(":")[1]));
                            changeMade = true;
                        }
                    }
                    catch (NumberFormatException nfex) {
                        caller.notice("Number parsing exception for argument " + arg + ". Skipping...");
                    }
                }

                if (changeMade) {
                    mobSpawner.update();
                    caller.message("Success!");
                }
                else {
                    caller.notice("Failure.");
                }
            }
            else {
                caller.notice("You need to be looking at a MobSpawner");
            }
        }
    }
}
