package net.canarymod.plugin;

import java.io.File;

/**
 * Interface defining methods that must be loaded by any plugin loader
 *
 * @author Pwootage
 */
public interface IPluginLoader {
    public PluginDescriptor load(File pluginFile);
}
