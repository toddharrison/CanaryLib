package net.canarymod.plugin.lifecycle;

import net.canarymod.exceptions.PluginLoadFailedException;
import net.canarymod.plugin.IPluginLifecycle;
import net.canarymod.plugin.IPluginManager;
import net.canarymod.plugin.Plugin;

/**
 * Created by Christopher on 9/7/2014.
 */
public class JavaPluginLifecycle extends PluginLifecycleBase {
    @Override
    public boolean enable(Plugin plugin, IPluginManager manager) {
        if (checkDeps(plugin, manager)) {
            return plugin.enable();
        }
    }

    @Override
    public boolean disable(Plugin plugin, IPluginManager manager) {
        return false;
    }

    @Override
    public Plugin load() throws PluginLoadFailedException {
        return null;
    }
}
