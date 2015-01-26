package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.tasks.ServerTask;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * @author Jason (darkdiplomat)
 */
public class DeleteWorldCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (!Canary.getServer().getWorldManager().worldExists(parameters[0])) {
            caller.notice("There was no world by that name.");
            return;
        }
        sendTranslatedNotice(caller, "world delete inprogress");
        Canary.getServer().addSynchronousTask(new DeleteWorldTask(caller, parameters[0]));
    }

    private class DeleteWorldTask extends ServerTask {
        private final MessageReceiver caller;
        private final String worldName;
        private int attempts = 0;

        DeleteWorldTask(MessageReceiver caller, String worldName) {
            super(Canary.getServer(), 1, true);
            this.caller = caller;
            this.worldName = worldName;
        }

        @Override
        public void run() {
            if (Canary.getServer().getWorldManager().worldIsLoaded(worldName)) {
                World world = Canary.getServer().getWorldManager().getWorld(worldName, false);
                if (!world.getPlayerList().isEmpty()) {
                    for (Player player : world.getPlayerList()) {
                        player.teleportTo(Canary.getServer().getDefaultWorld().getSpawnLocation());
                        player.notice(Translator.localTranslate("world deleted transfer", player.getLocale()));
                    }
                }
                Canary.getServer().getWorldManager().unloadWorld(worldName.replaceAll("_.+", ""), DimensionType.fromName(worldName.replaceAll(".+_", "")), true);
                return; // Wait for the unloading
            }

            if (attempts <= 10) {
                attempts++;
                if (Canary.getServer().getWorldManager().destroyWorld(worldName)) {
                    caller.notice(Translator.localTranslate("world delete success", caller.getLocale(), worldName));
                    Canary.getServer().removeSynchronousTask(this);
                    return;
                }
            }
            caller.notice(Translator.localTranslate("world delete success", caller.getLocale(), worldName));
            Canary.getServer().removeSynchronousTask(this);
            return;
        }
    }
}
