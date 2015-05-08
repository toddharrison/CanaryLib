package net.canarymod.database;

import net.canarymod.database.exceptions.DatabaseException;

import javax.persistence.EntityManager;
import java.text.MessageFormat;

/**
 * This is a DdlProvider specifically relying on Hibernate.
 * It's here because Canary uses Hibernate as default JPA implementation.
 */
public class HibernateDdlProvider implements DdlProvider {
    @Override
    public String createDdl(EntityManager entityManager) throws DatabaseException {
        // If this throws CCEs we have a mis-configured DbFactory.
        try {
            // TODO: get configuration from entity manager, pass config to SchemaExport class and do magic
            // Look here: http://jandrewthompson.blogspot.de/2009/10/how-to-generate-ddl-scripts-from.html
//            Session session = (Session)entityManager.getDelegate();
//            Configuration cfg = session.getSessionFactory().;
        }
        catch (ClassCastException e) {
            throw new DatabaseException(MessageFormat.format("Found an EntityManager delegate '{0}' in HibernateDdlProvider. Expected org.hibernate.Session", entityManager.getDelegate().getClass().getName()), e);
        }

        return null;
    }
}
