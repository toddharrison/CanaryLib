package net.canarymod.commandsys.commands.system.reservelist;

import net.canarymod.Canary;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Canary.reservelist;

/**
 * Reservelist Show (List) Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class ReservelistShow implements NativeCommand {

    @Override
    public void execute(MessageReceiver receiver, String[] args) {
        receiver.message(ChatFormat.AQUA + "**** RESERVATIONS ****");
        StringBuilder sb = new StringBuilder();
        for (String uuid : reservelist().getReservations()) {
            PlayerReference reference = Canary.getServer().matchKnownPlayer(uuid);
            if (reference != null) {
                sb.append(reference.getName()).append(", ");
            }
            else {
                sb.append(uuid).append(" (Name Unknown)").append(", ");
            }
        }

        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length() - 1);
        }
        else {
            sb.append(ChatFormat.RED).append("NONE");
        }
        receiver.message(sb.toString());
    }
}
