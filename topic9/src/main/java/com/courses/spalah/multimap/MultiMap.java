package com.courses.spalah.multimap;

import java.util.Collection;

/**
 * Basic interface for multimap collection.
 * Multimap - is a map which contains keys, and value is a collection of elements.
 *
 * @author Ievgen Tararaka
 */
public interface MultiMap<K, V> {
    /**
     * Stores a key-value pair in this multimap.
     *
     * @param key   key
     * @param value value to store in inner collection
     * @return {@code true} if the multimap changed
     */
    boolean put(K key, V value);

    /**
     * Get value-collection from this multimap.
     *
     * @param key associated key
     * @return collection
     */
    Collection<V> get(K key);

    /**
     * Remove all associated values for given key in this multimap.
     *
     * @param key associated key
     * @return removed collection by key
     */
    Collection<V> removeAll(K key);

    /**
     * Remove single value for given key.
     *
     * @param key   associated key
     * @param value value to remove
     * @return {@code true} if the multimap changed
     */
    boolean remove(K key, V value);

    /**
     * Return all values for all keys as single collection.
     *
     * @return all values
     */
    Collection<V> allValues();

    /**
     * Check if multimap contains some value
     *
     * @param value value to check
     * @return {@code true} if the multimap contains given value
     */
    boolean containsValue(V value);

    /**
     * Check if this multimap is empty
     *
     * @return {@code true} if multimap is empty
     */
    boolean isEmpty();

    /**
     * Return size of current multimap.
     * Size is equals to values total count.
     *
     * @return count of all values
     */
    int size();
}
