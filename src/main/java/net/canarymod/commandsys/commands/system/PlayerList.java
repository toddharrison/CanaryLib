package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;

import java.util.List;

/**
 * Command to list the players currently connected to the server
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class PlayerList implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        String world = parameters.length == 0 ? null : parameters[0];
        if (world != null) {
            if (!world.contains("_") || (!world.endsWith("_") && !DimensionType.typeExists(world.substring(world.lastIndexOf("_") + 1, world.length())))) {
                world += "_NORMAL"; // Append a dimension
            }
            if (!Canary.getServer().getWorldManager().worldExists(world)) {
                caller.notice(Translator.localTranslate("unknown world", caller.getLocale(), world));
                return;
            }
            else if (!Canary.getServer().getWorldManager().worldIsLoaded(world)) {
                caller.notice(Translator.localTranslate("unknown world", caller.getLocale(), world));
                return;
            }
        }
        caller.notice("**** PLAYERS ****");
        String list = createList(Canary.getServer().getWorld(world));
        if (caller.getReceiverType() != ReceiverType.PLAYER) {
            caller.notice(ChatFormat.removeFormatting(list));
        }
        else {
            caller.message(list);
        }
    }

    private String createList(World world) {
        List<Player> players = world != null ? world.getPlayerList() : Canary.getServer().getPlayerList();
        StringBuilder sb = new StringBuilder();

        for (Player p : players) {
            sb.append(p.getPrefix()).append(p.getName()).append(ChatFormat.WHITE).append(", ");
        }

        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length() - 1);
        }
        else {
            sb.append(ChatFormat.RED).append("NONE");
        }
        return sb.toString();
    }
}
