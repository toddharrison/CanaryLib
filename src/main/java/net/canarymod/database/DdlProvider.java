package net.canarymod.database;

import net.canarymod.database.exceptions.DatabaseException;

import javax.persistence.EntityManager;

/**
 * A Data Structure Definition provider.
 * This is used to scan the database and the entity schemata, detect changes
 * and output the resulting SQL that needs to be applied to the database
 * in order to keep db schema and entity schema synchronised.
 * If you plan on adding your own JPA implementation to canary, you must also specify
 * an implementation of this DdlProvider.
 * Otherwise server administrators cannot be informed about required database updates.
 */
public interface DdlProvider {
    /**
     * Returns the sql to update a database.
     * @param entityManager the entity manager
     * @return a string of sql statements
     */
    String createDdl(EntityManager entityManager) throws DatabaseException;
}
