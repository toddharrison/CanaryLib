package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.animal.Tameable;
import net.canarymod.api.world.World;
import net.canarymod.chat.MessageReceiver;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Mob count subcommnand
 *
 * @author Jason (darkdiplomat)
 */
public final class MobCount extends MobCommand {

    public void execute(MessageReceiver caller, String[] args) {
        World world = callerWorld(caller);
        if (args.length > 0) {
            String wrld = args[0];
            if (!wrld.matches("[\\w]+_(?i)(END|NORMAL|NETHER)")) {
                if (args.length > 1 && !args[1].matches("(?i)(END|NORMAL|NETHER)")) {
                    sendTranslatedNotice(caller, "mob worldname");
                    return;
                }
                wrld += "_" + args[1].toUpperCase();
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
                else {
                    passivesCount++;
                }
            }
            else if (entity.isLiving()) {
                passivesCount++;
            }
            else {
                utilityCount++;
            }
        }
        sendTranslatedNotice(caller, "mob count", mobCount, passivesCount, tameCount, utilityCount, (mobCount + passivesCount + tameCount + utilityCount), world.getFqName());
    }
}
