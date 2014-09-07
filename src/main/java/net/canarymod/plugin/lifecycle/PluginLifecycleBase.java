package net.canarymod.plugin.lifecycle;

import net.canarymod.exceptions.PluginDependencyException;
import net.canarymod.plugin.IPluginLifecycle;
import net.canarymod.plugin.IPluginManager;
import net.canarymod.plugin.Plugin;

/**
 * Created by Christopher on 9/7/2014.
 */
public abstract class PluginLifecycleBase implements IPluginLifecycle {
    protected void checkDeps(Plugin plugin, IPluginManager manager) throws PluginDependencyException {
        plugin.getDescriptor().getDependencies();
        return false;
    }
}
