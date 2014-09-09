package net.canarymod.plugin.lifecycle;

import net.canarymod.Canary;
import net.canarymod.CanaryClassLoader;
import net.canarymod.exceptions.PluginLoadFailedException;
import net.canarymod.plugin.*;

import java.io.File;

/**
 * Base class for plugin lifecycles
 *
 * @author Pwootage
 */
abstract class PluginLifecycleBase implements IPluginLifecycle {
    protected PluginDescriptor desc;

    public PluginLifecycleBase(PluginDescriptor desc) {
        this.desc = desc;
    }

    /**
     * {@inheritDoc}
     */
    public boolean enable(IPluginManager manager) {
        if (desc.getCurrentState() == PluginState.ENABLED) {
            return true;
        }
        if (desc.getCurrentState() == PluginState.DISABLED) {
            boolean en = desc.getPlugin().enable();
            if (en) {
                desc.setCurrentState(PluginState.ENABLED);
            }
            return en;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean disable(IPluginManager manager) {
        if (desc.getCurrentState() == PluginState.DISABLED) {
            return true;
        }
        if (desc.getCurrentState() == PluginState.ENABLED) {
            desc.getPlugin().disable();
            desc.setCurrentState(PluginState.DISABLED);
            Plugin p = desc.getPlugin();
            Canary.hooks().unregisterPluginListeners(p);
            Canary.commands().unregisterCommands(p);
            Canary.motd().unregisterMOTDListener(p);
            return true;
        }
        return false;
    }

    /**
     * Implement this method in order to do actual loading. Be sure to set the plugin object in
     * {@link net.canarymod.plugin.lifecycle.PluginLifecycleBase#desc}
     *
     * @param manager
     */
    protected abstract void _load(IPluginManager manager) throws PluginLoadFailedException;

    /**
     * {@inheritDoc}
     */
    public Plugin load(IPluginManager manager) throws PluginLoadFailedException {
        unload(manager);
        _load(manager);
        desc.setCurrentState(PluginState.DISABLED);
        return desc.getPlugin();

    }

    /**
     * Implement this method in order to do specific unloading code
     *
     * @param manager Manager passed to unload
     */
    protected abstract void _unload(IPluginManager manager);

    /**
     * {@inheritDoc}
     */
    public void unload(IPluginManager manager) {
        disable(manager);
        _unload(manager);
        desc.setPlugin(null);
        desc.setCurrentState(PluginState.KNOWN);
    }
}
