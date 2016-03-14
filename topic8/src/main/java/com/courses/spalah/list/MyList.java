package com.courses.spalah.list;

/**
 * @author Ievgen Tararaka
 */
public interface MyList<E> extends Iterable<E> {

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Append element to list
     *
     * @param element element to be appended to list
     * @return {@code true}
     */
    boolean add(E element);

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    void add(int index, E element);

    /**
     * Removes the element at the specified position in this list
     *
     * @param index the index of the element to be removed
     */
    void remove(int index);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    E get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    E set(int index, E element);

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param element element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    boolean contains(E element);
}
