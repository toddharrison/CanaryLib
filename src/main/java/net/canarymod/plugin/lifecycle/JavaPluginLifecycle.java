package net.canarymod.plugin.lifecycle;

import net.canarymod.Canary;
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
public class JavaPluginLifecycle extends PluginLifecycleBase {
    private CanaryClassLoader ploader;

    public JavaPluginLifecycle(PluginDescriptor desc) {
        super(desc);
    }

    @Override
    protected void _load(IPluginManager manager) throws PluginLoadFailedException {
        try {
            ploader = new CanaryClassLoader(new File(desc.getPath()).toURI().toURL(), getClass().getClassLoader());
            Class<?> cls = ploader.loadClass(desc.getCanaryInf().getString("main-class"));
            //A hacky way of getting the name in during the constructor/initializer
            Plugin.threadLocalName.set(desc.getName());
            Plugin p = (Plugin) cls.newInstance();
            p.setPriority(desc.getPriority());
            desc.setPlugin(p);
        } catch (Exception e) {
            throw new PluginLoadFailedException("Failed to load plugin", e);
        }
    }

    @Override
    protected void _unload(IPluginManager manager) {
        if (ploader != null) {
            ploader.close();
        }
    }

}
