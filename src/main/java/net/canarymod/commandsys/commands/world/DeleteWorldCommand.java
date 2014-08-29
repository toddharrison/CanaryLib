package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.tasks.ServerTask;

/**
 * @author Jason (darkdiplomat)
 */
public class DeleteWorldCommand {

    public void execute(MessageReceiver caller, String[] parameters) {

    }

    private class DeleteWorldTask extends ServerTask {
        private final MessageReceiver caller;
        private final String worldName;

        DeleteWorldTask(MessageReceiver caller, String worldName) {
            super(Canary.getServer(), 5, true);
            this.caller = caller;
            this.worldName = worldName;
        }

        @Override
        public void run() {
            if (!Canary.getServer().getWorldManager().worldIsLoaded(worldName)) {
                Canary.getServer().getWorldManager().destroyWorld(worldName);
                caller.notice(String.format("World '%s' deleted", worldName));
                Canary.getServer().removeSynchronousTask(this);
            }
        }
    }
}
