package net.canarymod.commandsys.commands.system.whitelist;

import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

import static net.canarymod.Translator.sendTranslatedMessage;

/**
 * Whitelist Disable (Off) Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class WhitelistDisable implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] args) {
        PropertiesFile srvcfg = Configuration.getServerConfig().getFile();
        srvcfg.setBoolean("whitelist-enabled", false);
        srvcfg.save();
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "whitelist disabled");
    }
}
