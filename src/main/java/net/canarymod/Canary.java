package net.canarymod;

import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.factory.Factory;
import net.canarymod.api.scoreboard.ScoreboardManager;
import net.canarymod.bansystem.BanManager;
import net.canarymod.channels.ChannelManager;
import net.canarymod.commandsys.CommandManager;
import net.canarymod.commandsys.PlayerSelector;
import net.canarymod.config.Configuration;
import net.canarymod.database.Database;
import net.canarymod.help.HelpManager;
import net.canarymod.hook.HookExecutor;
import net.canarymod.kit.KitProvider;
import net.canarymod.logger.Logman;
import net.canarymod.motd.MessageOfTheDay;
import net.canarymod.permissionsystem.PermissionManager;
import net.canarymod.plugin.IPluginManager;
import net.canarymod.serialize.Serializer;
import net.canarymod.tasks.TaskOwner;
import net.canarymod.user.OperatorsProvider;
import net.canarymod.user.ReservelistProvider;
import net.canarymod.user.UserAndGroupsProvider;
import net.canarymod.user.WhitelistProvider;
import net.canarymod.warp.WarpProvider;
import net.visualillusionsent.utils.JarUtils;
import java.util.HashMap;

/**
 * The interface to the brains of the bird! AKA Utils
 *
 * @author Chris (damagefilter)
 * @author Jos Kuijpers
 * @author Brian (WWOL)
 * @author Jason (darkdiplomat)
 */
public abstract class Canary implements TaskOwner {
    public final static Logman log;
    private static boolean pluginsUp;
    private static String jarPath;
    protected Server server;

    protected BanManager banManager;
    protected UserAndGroupsProvider userAndGroupsProvider;
    protected PermissionManager permissionManager;
    protected WarpProvider warpProvider;
    protected KitProvider kitProvider;
    protected WhitelistProvider whitelist;
    protected OperatorsProvider ops;
    protected ReservelistProvider reservelist;
    protected HookExecutor hookExecutor;
    protected Database database;
    protected IPluginManager pluginManager;
    protected HelpManager helpManager;
    protected CommandManager commandManager;
    protected Factory factory;
    protected ChannelManager channelManager;
    protected ScoreboardManager scoreboardManager;
    protected MessageOfTheDay motd;
    protected PlayerSelector playerSelector;

    // Serializer Cache
    HashMap<Class<?>, Serializer<?>> serializers = new HashMap<Class<?>, Serializer<?>>();

    protected static Canary instance;

    static {
        System.out.println("Please wait while the libraries initialize...");
        log = Logman.getLogman("CanaryMod");
    }

    /**
     * Get the ban System to manage bans
     *
     * @return {@link BanManager}
     */
    public static BanManager bans() {
        return instance.banManager;
    }

    /**
     * Get the Groups provider to manage groups
     *
     * @return {@link UserAndGroupsProvider}
     */
    public static UserAndGroupsProvider usersAndGroups() {
        return instance.userAndGroupsProvider;
    }

    /**
     * Get the Warps provider to manage warps and homes
     *
     * @return {@link WarpProvider}
     */
    public static WarpProvider warps() {
        return instance.warpProvider;
    }

    /**
     * Get the Kit Provider to manage kits
     *
     * @return {@link KitProvider}
     */
    public static KitProvider kits() {
        return instance.kitProvider;
    }

    /**
     * Get the whitelist provider for managing the whitelist
     *
     * @return {@link WhitelistProvider}
     */
    public static WhitelistProvider whitelist() {
        return instance.whitelist;
    }

    /**
     * Get the operators provider for managing the ops
     *
     * @return {@link OperatorsProvider}
     */
    public static OperatorsProvider ops() {
        return instance.ops;
    }

    /**
     * Get the reservelist provider for managing the reservelist
     *
     * @return {@link ReservelistProvider}
     */
    public static ReservelistProvider reservelist() {
        return instance.reservelist;
    }

    /**
     * Get the Hook executor to fire hooks
     *
     * @return {@link HookExecutor}
     */
    public static HookExecutor hooks() {
        return instance.hookExecutor;
    }

    /**
     * Get the database interface for managing system data and custom plugin data
     *
     * @return {@link Database}
     */
    public static Database db() {
        return instance.database;
    }

    /**
     * @deprecated Use {@link Canary#manager()}
     *
     * @return {@link net.canarymod.plugin.PluginManager}
     */
    public static IPluginManager loader() {
        return instance.pluginManager;
    }

    /**
     * Get the Plugin Manager to load, enable or disable plugins and manage
     * plugin dependencies
     *
     * @return {@link net.canarymod.plugin.PluginManager}
     */
    public static IPluginManager manager() {
        return instance.pluginManager;
    }

    /**
     * Get the permission loader.
     * Note: As plugin author will rarely need to use this.
     * Use the PermissionProviders with Groups and players instead!
     *
     * @return {@link PermissionManager}
     */
    public static PermissionManager permissionManager() {
        return instance.permissionManager;
    }

    /**
     * Get the help manager, used to register and unregister help commands, and creating help visualizations
     *
     * @return {@link HelpManager}
     */
    public static HelpManager help() {
        return instance.helpManager;
    }

    /**
     * Get the command manager, used to register and unregister commands.
     *
     * @return The current {@link CommandManager} instance.
     */
    public static CommandManager commands() {
        return instance.commandManager;
    }

    /**
     * Gets the {@link Factory} manager
     *
     * @return {@link Factory}
     */
    public static Factory factory() {
        return instance.factory;
    }

    /**
     * Gets the {@link ChannelManager}
     *
     * @return {@link ChannelManager}
     */
    public static ChannelManager channels() {
        return instance.channelManager;
    }

    /**
     * Gets the {@link ScoreboardManager}
     *
     * @return {@link ScoreboardManager}
     */
    public static ScoreboardManager scoreboards() {
        return instance.scoreboardManager;
    }

    public static MessageOfTheDay motd() {
        return instance.motd;
    }
    
    /**
     * Gets the {@link PlayerSelector}
     * 
     * @return {@link PlayerSelector}
     */
    public static PlayerSelector playerSelector() {
        return instance.playerSelector;
    }

    /**
     * Get the canary instance
     *
     * @return {@link Canary}
     */
    public static Canary instance() {
        return instance;
    }

    /**
     * Set the canary instance
     *
     * @param canary
     *         the {@link Canary} instance
     */
    public static void setCanary(Canary canary) {
        if (instance == null) {
            instance = canary;
        }
    }

    /**
     * Set the server instance for this Canary
     *
     * @param server
     *         the {@link Server} instance
     */
    public static void setServer(Server server) {
        instance.server = server;
    }

    /** Enables all plugins */
    public static void enablePlugins() {
        if (!pluginsUp && instance.server != null) {
            log.info("Enabling Plugins...");
            manager().enableAllPlugins();
            pluginsUp = true;
        }
    }

    /**
     * Get the Server for managing server related stuff
     *
     * @return {@link Server}
     */
    public static Server getServer() {
        return instance.server;
    }

    /**
     * Serialize an object of the given Type T into a String.
     *
     * @param object
     *         the {@link Object} to serialize
     *
     * @return serialized {@link String} of the object or null if there is no suitable serializer registered
     */
    @SuppressWarnings("unchecked")
    public static <T> String serialize(T object) {
        Serializer<T> ser = (Serializer<T>) instance.serializers.get(object.getClass());

        if (ser != null) {
            return ser.serialize(object);
        }
        return null;
    }

    /**
     * Accepts a String with data and the type it should deserialize into.
     *
     * @param data
     *         the data to have deserialized
     * @param shell
     *         object of given type or null if there is no suitable serializer registered
     *
     * @return deserialized data
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String data, Class<T> shell) {
        Serializer<T> ser = (Serializer<T>) instance.serializers.get(shell);

        if (ser != null) {
            try {
                return ser.deserialize(data);
            }
            catch (CanaryDeserializeException e) {
                log.error("Deserialization failure.", e);
            }
        }
        return null;
    }

    /**
     * Add a serializer to the system
     *
     * @param serializer
     *         the {@link Serializer} to add
     * @param type
     *         The type this serializer can process
     */
    public static void addSerializer(Serializer<?> serializer, Class<?> type) {
        log.debug("Adding a new Serializer: " + type);
        instance.serializers.put(type, serializer);
    }

    /**
     * Reload all subsystems and the whole of canary.
     * Don't over-use this method, it slows down the server.
     * It is used by the reload command and should not be used by anything else!
     */
    public void reload() {

        // Reload configurations
        Configuration.reload();

        // Reload all subsystems with a cache
        instance.banManager.reload();
        instance.kitProvider.reload();
        instance.userAndGroupsProvider.reloadAll();
        instance.warpProvider.reload();
        instance.whitelist.reload();
        instance.reservelist.reload();
        instance.ops.reload();
        instance.motd.reload();

        // Reload Player permissions and groups data
        for (Player p : getServer().getPlayerList()) {
            userAndGroupsProvider.addOrUpdatePlayerData(p);
        }
    }

    /**
     * Sets the Specification Version
     *
     * @return specification version
     *
     * @see Package#getSpecificationVersion()
     */
    public static String getSpecificationVersion() {
        return Canary.class.getPackage().getSpecificationVersion();
    }

    /**
     * Gets the Specification Title
     *
     * @return specification title
     *
     * @see Package#getSpecificationTitle()
     */
    public static String getSpecificationTitle() {
        return Canary.class.getPackage().getSpecificationTitle();
    }

    /**
     * Gets the Implementation Version
     *
     * @return implementation version
     *
     * @see Package#getImplementationVersion()
     */
    public static String getImplementationVersion() {
        return Canary.class.getPackage().getImplementationVersion();
    }

    /**
     * Gets the Implementation Title
     *
     * @return implementation title
     *
     * @see Package#getImplementationTitle()
     */
    public static String getImplementationTitle() {
        return Canary.class.getPackage().getImplementationTitle();
    }

    /**
     * Gets the file path for the Canary jar file
     *
     * @return the Canary Jar file path
     */
    public static String getCanaryJarPath() {
        if (jarPath == null) {
            jarPath = JarUtils.getJarPath(Canary.class);
        }
        return jarPath;
    }
}
