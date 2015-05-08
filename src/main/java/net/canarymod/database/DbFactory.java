package net.canarymod.database;

import net.canarymod.Canary;
import net.canarymod.config.Configuration;
import net.canarymod.database.exceptions.DatabaseException;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.visualillusionsent.utils.PropertiesFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.MessageFormat;
import java.util.*;

/**
 * Utility class to instance a hibernate session.
 */
public class DbFactory {
    private static DbFactory instance;

    private Logman log;

    /**
     * Get the session manager factory instance
     * @return DbFactory instance of DbFactory
     */
    public static DbFactory instance() {
        if (instance == null) {
            Logman log = Logman.getLogman("DbFactory");
            try {
                instance = new DbFactory(log);
            }
            catch (Throwable t) {
                log.error("Failed to make a new db factory instance!", t);
            }
        }
        return instance;
    }

    private Map<String,EntityManagerFactory> factoryMap;
    private Map<String, EntityManager> instanceMap;

    private DbFactory(Logman logman) {
        this.log = logman;
        this.factoryMap = new HashMap<String, EntityManagerFactory>();
        this.instanceMap = new HashMap<String, EntityManager>();
        this.createEntityManagerFactory(Configuration.getDbConfig().getFile(), "canary_default", new HibernateDdlProvider());
    }

    /***
     * Return the entity manager with the specified persistence unit name.
     * If you have registered an alternative entity manager, you'll be able to
     * get it this way. Be aware though that Canary itself will always use its own default entity manager.
     *
     * @param persistenceUnitName the name
     * @return an entity manager.
     * @throws DatabaseException
     */
    public EntityManager getEntityManager(String persistenceUnitName) throws DatabaseException {
        EntityManager em = this.instanceMap.get(persistenceUnitName);
        if (em == null || !em.isOpen()) {
            if (!this.factoryMap.containsKey(persistenceUnitName)) {
                throw new DatabaseException(MessageFormat.format("{0} has no registered EntityManagerFactory. Cannot get EntityManager!", persistenceUnitName));
            }
            em = this.factoryMap.get(persistenceUnitName).createEntityManager();
            this.instanceMap.put(persistenceUnitName, em);
        }
        return em;
    }

    /**
     * Creates and registers a new entity manager factory.
     * Use this to add or override existing entity manager factories.
     * Be wary that replacing an existing factory during runtime may cause some
     * serious trouble with the data!
     *
     * @param props the configuration for the entity manager. For hard-coded persistence units, these values represent additionals and/or overrides
     * @param persistenceUnitName the name of the persistence unit.
     */
    public void createEntityManagerFactory(PropertiesFile props, String persistenceUnitName, DdlProvider ddlProvider) {
        // Entity classes must be treated specially.
        // As a note: canary default entities are specified in the persistence.xml and are loaded automatically
        // when the default persistence unit gets instantiated
        List<String> classes = new ArrayList<String>();
        if (props.containsKey("class")) {
            Collections.addAll(classes, props.getStringArray("class"));
            props.removeKey("class");
        }
        else if (props.containsKey("classes")) {
            Collections.addAll(classes, props.getStringArray("classes"));
            props.removeKey("classes");
        }
        // load entities provided by plugins
        for(Plugin p : Canary.pluginManager().getPlugins()) {
            for (Class<?> c : p.getDatabaseEntities()) {
                classes.add(c.getName());
            }
        }
        Properties jprops = new Properties();
        jprops.putAll(props.getPropertiesMap());
        jprops.put("classes", classes);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName, jprops);
        this.factoryMap.put(persistenceUnitName, emf);
    }
}
