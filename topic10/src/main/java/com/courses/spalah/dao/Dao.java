package com.courses.spalah.dao;

import java.util.List;

/**
 * @author Ievgen Tararaka
 */
public interface Dao<T> {
    /**
     * Find all entities in data base
     *
     * @return list of found entities
     */
    List<T> findAll();

    /**
     * Find entity by id
     *
     * @param id identifier of entity
     * @return entity if found, <code>null</code> in other cases
     */
    T findById(Long id);

    /**
     * Update entity. Search it by id and then update.
     *
     * @param entity to be updated
     * @return <code>true</code> if updated, else <code>false</code>
     */
    boolean update(T entity);

    /**
     * Insert new entity. Does not insert entity if entity with same ID exists
     *
     * @param entity to be inserted
     * @return <code>true</code> if inserted, else <code>false</code>
     */
    boolean insert(T entity);

    /**
     * Remove entity from data base by id
     *
     * @param id of entity to remove
     * @return removed entity
     */
    T remove(Long id);
}
