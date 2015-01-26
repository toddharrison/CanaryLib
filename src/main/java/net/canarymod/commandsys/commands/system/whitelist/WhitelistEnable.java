package net.canarymod.commandsys.commands.system.whitelist;

import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

import static net.canarymod.Translator.sendTranslatedMessage;

/**
 * Whitelist Enable (On) Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class WhitelistEnable implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] args) {
        PropertiesFile srvcfg = Configuration.getServerConfig().getFile();
        srvcfg.setBoolean("whitelist-enabled", true);
        srvcfg.save();
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "whitelist enabled");
    }
}
