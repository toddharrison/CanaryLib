package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.animal.Tameable;
import net.canarymod.api.world.World;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Mob Clearing subcommnand
 *
 * @author Jason (darkdiplomat)
 */
public final class MobClear extends MobCommand {

    public void execute(MessageReceiver caller, String[] args) {
        boolean hostiles = false, passives = false, tamed = false, utility = false, all = false;
        long radius = 20L;
        World world = callerWorld(caller);

        if (args[0].contains("h")) {
            hostiles = true;
        }
        if (args[0].contains("p")) {
            passives = true;
        }
        if (args[0].contains("t")) {
            tamed = true;
        }
        if (args[0].contains("a")) {
            all = true;
        }
        if (args[0].contains("u")) {
            utility = true;
        }

        if (!(hostiles || passives || tamed || utility || all)) {
            sendTranslatedNotice(caller, "mob noflags");
            return;
        }

        if (args.length > 1) {
            int index = 1;
            String rad = args[index];
            if (rad.matches("\\d+")) {
                index++;
                try {
                    radius = Long.parseLong(rad);
                }
                catch (NumberFormatException nfe) {
                    caller.notice(Translator.translateAndFormat("mob radius", rad));
                }
                if (radius < -1 || radius == 0 || radius > 500) { // Set maximum radius
                    radius = 20;
                    sendTranslatedNotice(caller, "mob radius", rad);
                }
            }

            if (args.length > index) {
                String wrld = args[index];
                if (!wrld.matches("[\\w]+_(?i)(END|NORMAL|NETHER)")) {
                    if (args.length > index + 1) {
                        if (!args[index + 1].matches("(?i)(END|NORMAL|NETHER)")) {
                            sendTranslatedNotice(caller, "mob worldname");
                            return;
                        }
                        wrld += "_" + args[index + 1].toUpperCase();
                    }
                }

                if (!Canary.getServer().getWorldManager().worldExists(wrld)) {
                    sendTranslatedNotice(caller, "mob worldexsistance", wrld);
                    return;
                }
                else if (!Canary.getServer().getWorldManager().worldIsLoaded(wrld)) {
                    sendTranslatedNotice(caller, "mob worldnoload");
                    return;
                }
                world = Canary.getServer().getWorld(wrld);
            }
        }

        int mobCount = 0, passivesCount = 0, utilityCount = 0, tameCount = 0;
        for (Entity entity : world.getTrackedEntities()) {
            if (entity.isPlayer()) {
                continue;
            }
            if (radius == -1 || entity.getLocation().getDistance(location(caller)) <= radius) {
                if (entity.isMob() && (hostiles || all)) {
                    entity.destroy();
                    mobCount++;
                }
                else if (entity.isAnimal() && (passives || all)) {
                    if (entity instanceof Tameable) {
                        if (tamed || all) {
                            entity.destroy();
                            tameCount++;
                        }
                    }
                    else {
                        entity.destroy();
                        passivesCount++;
                    }
                }
                else if (entity.isLiving() && !entity.isMob() && (passives || all)) {
                    entity.destroy();
                    passivesCount++;
                }
                else if (!entity.isLiving() && (utility || all)) {
                    entity.destroy();
                    utilityCount++;
                }
            }
        }
        sendTranslatedNotice(caller, "mob removecount", mobCount, passivesCount, tameCount, utilityCount, (mobCount + passivesCount + tameCount + utilityCount));
    }
}
