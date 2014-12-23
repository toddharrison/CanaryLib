package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.Translator;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import java.util.UUID;

/**
 * Command to unban a player
 *
 * @author Chris (damagefilter)
 */
public class UnbanCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] cmd) {
        Player p = Canary.getServer().getPlayer(cmd[0]);
        String uuid = null;
        if (p != null) {
            uuid = p.getUUIDString();
        }
        else {
            OfflinePlayer op = Canary.getServer().getOfflinePlayer(cmd[0]);
            if (op != null) {
                uuid = op.getUUIDString();
            }
            else {
                // Look up at the mojang API
                UUID uid = ToolBox.uuidFromUsername(cmd[0]);
                if (uid != null) {
                    uuid = uid.toString();
                }
            }
        }
        if (uuid != null) {
            Canary.bans().unban(uuid);
            caller.message(ChatFormat.YELLOW + Translator.translateAndFormat("unban success", cmd[0]));
        }
        else {
            caller.message(ChatFormat.YELLOW + Translator.translateAndFormat("unban failed invalid user", cmd[0]));
        }
    }
}
