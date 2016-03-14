package com.courses.spalah.list;

/**
 * @author Ievgen Tararaka
 */
public class ListFactory {
    public static <E> MyList<E> createList(ListType listType) {
        if (listType == ListType.SINGLY_LINKED_LIST) {
            return new SinglyLinkedList<E>();
        }
        return null;
    }
}
