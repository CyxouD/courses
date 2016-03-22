package com.courses.spalah.list;

import com.sun.prism.shader.Solid_TextureFirstPassLCD_AlphaTest_Loader;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Dima on 13.03.2016.
 */
public class SinglyLinkedList<E> implements MyList<E> {
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
    public boolean add(E element) {
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
    public void add(int index, E element) {
        if (index < 0 || index >= size) throw new ListIndexOutOfBoundsException();

        if (isIndexAtCornerCases(index)){
            processCornerCase(index, (E) element);
        }
        else {
            Node foundNode = trackNodeAtIndex(index - 1);
            Node newNode = new Node();
            newNode.element = (E) element;
            newNode.next = foundNode.next;
            foundNode.next = newNode;
        }
        size++;
    }


    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) throw new ListIndexOutOfBoundsException();

        if (index == 0){
            first = first.next;
        }
        else {
            Node foundNode = trackNodeAtIndex(index - 1);
            if (index == size) {
                last = foundNode;
            }
            foundNode.next = foundNode.next.next;
        }
        size--;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) throw new ListIndexOutOfBoundsException();
        return trackNodeAtIndex(index).element;
    }


    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) throw new ListIndexOutOfBoundsException();

        Node foundNode = trackNodeAtIndex(index);
        E savedElement = foundNode.element;
        foundNode.element = (E) element;
        return savedElement;
    }


    @Override
    public boolean contains(E element) {
        for (E e : this){
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
            SinglyLinkedList.this.remove(curN);
            sizeList--;
        }

    }

    private static class ListIndexOutOfBoundsException extends IndexOutOfBoundsException{

    }

    public static void main(String[] args){

        Car civic = new Car(20_000, "Honda Civic");
        Car m3 = new Car(50_000, "MMW M3");
        Car auris = new Car(18_000, "Toyota auris");
        Car sx4 = new Car(14_000, "Suzuki SX4");
        ArrayList<Car> carList = new ArrayList<>();
       // carList.add(civic);
       // carList.add(m3);
        carList.add(auris);
        carList.add(sx4);

        Iterator<Car> iterator = carList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove(); // auris // [empty]
            System.out.println(carList.size());
        }
        System.out.println(carList.isEmpty());

    }
}
