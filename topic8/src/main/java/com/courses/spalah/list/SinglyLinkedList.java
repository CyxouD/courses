package com.courses.spalah.list;

import com.sun.prism.shader.Solid_TextureFirstPassLCD_AlphaTest_Loader;

import java.util.Iterator;

/**
 * Created by Dima on 13.03.2016.
 */
public class SinglyLinkedList<E> implements MyList {
    private int size;
    private Node first;
    private Node last;

    private class Node{
        E element;
        Node next;
    }

    public SinglyLinkedList(){
        first = null;
        last = null;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(Object element) {
        Node oldLast = last;
        last = new Node();
        last.element = (E) element;
        last.next = first;

        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
        return true;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index >= size) throw new ListIndexOutOfBoundsException();
        size++;

        if (isIndexAtCornerCases(index)){
            processCornerCase(index, (E) element);
            return;
        }

        Node foundNode = trackNodeAtIndex(index - 1);
        Node newNode = new Node();
        newNode.element = (E) element;
        newNode.next = foundNode.next;
        foundNode.next = newNode;

    }


    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) throw new ListIndexOutOfBoundsException();
        size--;

        if (index == 0){
            first = first.next;
            return;
        }

        Node foundNode = trackNodeAtIndex(index - 1);
        if (index == size)
            last = foundNode;
        foundNode.next = foundNode.next.next;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) throw new ListIndexOutOfBoundsException();
        return trackNodeAtIndex(index).element;
    }


    @Override
    public Object set(int index, Object element) {
        if (index < 0 || index >= size) throw new ListIndexOutOfBoundsException();

        Node foundNode = trackNodeAtIndex(index);
        E savedElement = foundNode.element;
        foundNode.element = (E) element;
        return savedElement;
    }


    @Override
    public boolean contains(Object element) {
        for (Object e : this){
            if (e.equals(element)) return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new SinglyLinkedListIterator();
    }

    private Node trackNodeAtIndex(int index){
        Node currentNode = first;
        int curI = 0;
        while (curI != index) {
            currentNode = currentNode.next;
            curI++;
        }
        return currentNode;
    }

    private boolean isIndexAtCornerCases(int index){
        if (index == 0 || index == size - 2) return true;
        return false;
    }


    private void processCornerCase(int index, E element){
        if (index == 0){
            addNewFirst(element);
        }
        else if (index == size - 2){
            addNewLast(element);
        }
    }

    private void addNewFirst(E element){
        Node oldFirst = first;
        first = new Node();
        first.element = (E) element;
        first.next = oldFirst;
        last.next = first;
    }

    private void addNewLast(E element){
        Node oldLast = last;
        last = new Node();
        oldLast.next = last;
        last.element = (E) element;
        last.next = first;
    }

    private class SinglyLinkedListIterator implements Iterator<E>{
        private Node current = first;
        private int curN;
        private int sizeList = size;

        @Override
        public boolean hasNext() {
            return curN != sizeList;
        }

        @Override
        public E next() {
                E element = current.element;
                current = current.next;
                curN++;
                return element;
        }

        @Override
        public void remove() {
            SinglyLinkedList.this.remove(0);
        }

    }

    private static class ListIndexOutOfBoundsException extends IndexOutOfBoundsException{

    }

    public static void main(String[] args){
    }
}
