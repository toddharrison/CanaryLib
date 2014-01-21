package net.canarymod.logger;

import net.canarymod.chat.MessageReceiver;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Canary specific Logger Levels
 *
 * @author Jason (darkdiplomat)
 * @author Larry1123
 *         Updated to make use of log4j
 */
public final class CanaryLevel implements Marker {

    private final Marker marker;

    /** Canary SERVERMESSAGE Level (801), For use with {@link MessageReceiver#message(String)} */
    public static final CanaryLevel SERVERMESSAGE = new CanaryLevel("SERVERMESSAGE");

    /** Canary CHAT Level (802). For use with loggin chat messages from players */
    public static final CanaryLevel CHAT = new CanaryLevel("CHAT");

    /** Canary Server NOTICE Level (901), For use with {@link MessageReceiver#notice(String)} */
    public static final CanaryLevel NOTICE = new CanaryLevel("NOTICE");

    /** Canary DERP Level (902), For those herp times */
    public static final CanaryLevel DERP = new CanaryLevel("DERP");

    /** Canary DEBUG Level (200), For internal debugging */ // Removed because log4j has a debug Level as it is

    /** Canary PLUGIN_DEBUG Level (201), For plugin debugging */
    public static final CanaryLevel PLUGIN_DEBUG = new CanaryLevel("PLUGIN_DEBUG");

    /**
     * Creates a new Canary Level
     *
     * @param name
     *         name of the level
     */
    private CanaryLevel(String name) {
        marker = MarkerManager.getMarker(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return marker.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker getParent() {
        return marker.getParent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInstanceOf(Marker m) {
        return marker.isInstanceOf(m);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInstanceOf(String name) {
        return marker.isInstanceOf(name);
    }

}
