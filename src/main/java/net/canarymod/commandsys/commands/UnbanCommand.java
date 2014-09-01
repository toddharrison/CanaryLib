package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to unban a player
 *
 * @author Chris (damagefilter)
 */
public class UnbanCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] cmd) {
        if (cmd.length < 2) {
            Canary.help().getHelp(caller, "unban");
            return;
        }
        Player p = Canary.getServer().getPlayer(cmd[1]);
        String subject = null;
        /* Check for UUID ban */
        if (p != null) {
            subject = p.getUUIDString();
            if (Canary.bans().isBanned(subject)) {
                Canary.bans().unban(subject);
                caller.message(Colors.YELLOW + Translator.translateAndFormat("unban success", cmd[1]));
                return;
            }
        }
        
        /* check for ip/playername ban */
        if (Canary.bans().isBanned(cmd[1])) {
            Canary.bans().unban(subject);
            caller.message(Colors.YELLOW + Translator.translateAndFormat("unban success", cmd[1]));
            return;
        }
        // TODO tell them something wasn't valid
        
    }
}
