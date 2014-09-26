package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.bansystem.Ban;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.hook.player.BanHook;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to ban players by name
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class BanCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length < 1) {
            Canary.help().getHelp(caller, "ban");
            return;
        }
        String reason = "Permanently Banned";
        long timestamp = -1L;

        reason = StringUtils.joinString(parameters, " ", 1);
        if (parameters.length >= 3) {
            try {
                timestamp = ToolBox.parseTime(Long.parseLong(parameters[parameters.length - 2]), parameters[parameters.length - 1]);
                reason = StringUtils.joinString(parameters, " ", 1, parameters.length - 3);
            }
            catch (NumberFormatException e) {
                timestamp = -1L;
            }
        }

        Player[] playerSelectorArray = Canary.playerSelector().matchPlayers(caller, parameters[0]);
        if (playerSelectorArray != null) {
            for (Player p : playerSelectorArray) {
                Ban ban = new Ban();
                ban.setReason(reason);
                ban.setTimestamp(timestamp);
                ban.setBanningPlayer(caller.getName());
                ban.setUUID(p.getUUIDString());
                ban.setSubject(p.getName());
                Canary.bans().issueBan(ban);
                Canary.hooks().callHook(new BanHook(p, p.getIP(), caller, reason, timestamp));
                caller.notice(Translator.translateAndFormat("ban banned", p.getName()));
                p.kick(reason);
            }
            return;
        }
        else {
            PlayerReference ref = Canary.getServer().matchKnownPlayer(parameters[0]);
            Ban ban = new Ban();
            ban.setReason(reason);
            ban.setTimestamp(timestamp);
            ban.setBanningPlayer(caller.getName());
            ban.setUUID(ref.getUUIDString());
            ban.setSubject(ref.getName());
            Canary.bans().issueBan(ban);
            Canary.hooks().callHook(new BanHook(ref, ref.getIP(), caller, reason, timestamp));
            caller.notice(Translator.translateAndFormat("ban banned", ref.getName()));
            if (ref.isOnline() && ref instanceof Player) {
                ((Player) ref).kick(reason);
            }
        }
    }
}
