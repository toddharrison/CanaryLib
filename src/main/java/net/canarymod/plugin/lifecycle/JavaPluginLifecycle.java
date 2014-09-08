package net.canarymod.plugin.lifecycle;

import net.canarymod.CanaryClassLoader;
import net.canarymod.exceptions.PluginLoadFailedException;
import net.canarymod.plugin.*;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Lifecycle manager for a java plugin
 *
 * @author Pwootage
 */
public class JavaPluginLifecycle implements IPluginLifecycle {
    private CanaryClassLoader ploader;
    private PluginDescriptor desc;

    public JavaPluginLifecycle(PluginDescriptor desc) {
        this.desc = desc;
    }

    @Override
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

    @Override
    public boolean disable(IPluginManager manager) {
        if (desc.getCurrentState() == PluginState.DISABLED) {
            return true;
        }
        if (desc.getCurrentState() == PluginState.ENABLED) {
            desc.getPlugin().disable();
            desc.setCurrentState(PluginState.DISABLED);
            return true;
        }
        return false;
    }

    @Override
    public Plugin load(IPluginManager manager) throws PluginLoadFailedException {
        try {
            unload(manager);
            ploader = new CanaryClassLoader(new File(desc.getPath()).toURI().toURL(), getClass().getClassLoader());
            Class<?> cls = ploader.loadClass(desc.getCanaryInf().getString("main-class"));
            Plugin p =  (Plugin) cls.newInstance();
            p.setPriority(desc.getPriority());
            desc.setPlugin(p);
            desc.setCurrentState(PluginState.DISABLED);
            return p;
        }
        catch (Exception e) {
            throw new PluginLoadFailedException("Failed to load plugin", e);
        }
    }

    @Override
    public void unload(IPluginManager manager) {
        disable(manager);
        if (ploader != null) {
            ploader.close();
        }
        desc.setPlugin(null);
        desc.setCurrentState(PluginState.KNOWN);
    }
}
