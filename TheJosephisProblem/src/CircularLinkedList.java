import java.util.Iterator;
import java.util.*;
import java.util.LinkedList;
// linked list vs arraylist : how do they waste space in different ways/contexts, how do they preform for edit operations (add, remove), how do they preform excess operations (set, get)
// prewrite the answer (last question of practice test)

public class CircularLinkedList<E> implements Iterable<E> {
    // My variables
    Node<E> head;
    Node<E> tail;
    int size;


    // implement this constructor
    public CircularLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    // I highly recommend using this helper method
    // Return Node<E> found at the specified index
    // be sure to handle out of bounds cases
    private Node<E> getNode(int index ) {
        // index out of bounds
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    // attach a node to the end of the list
    public boolean add(E item) {
        this.add(size,item);
        return true;
    }

    public void add(int index, E item){
        // index out of bounds
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        Node<E> adding = new Node<>(item);

        // adding to a empty list
        if(size == 0) {
            this.head = adding;
            this.tail = adding;
            tail.next = head;
        }
        // adding a new head (adding to front)
        else if(index == 0) {
            adding.next = head;
            head = adding;
            tail.next = head;
        }
        // adding a new tail (adding to end)
        else if(index == size) {
            tail.next = adding;
            tail = adding;
            tail.next = head;
        }
        // adding anywhere else
        else {
            Node<E> before = getNode(index-1);
            adding.next = before.next;
            before.next = adding;
        }

        size++;
    }

    // removing the last thing
    public E remove(int index) {
        // index out of bounds
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E toReturn = null;
        // removing the only thing in the list
        if(size == 1) {
            toReturn = head.item;
            head = null;
            tail = null;
        }
        // removing the head (removing first thing in list)
        //  (need to adjust the last thing in the list to point to beginning
        else if(index == 0) {
            toReturn = head.item;
            head = head.next;
            tail.next = head;
        }
        // removing the tail (last thing in the list)
        else if (index == size - 1) {
            Node<E> before = getNode(index-1);
            toReturn = tail.item;
            tail = before;
            tail.next = head;
        }
        // removing any other node
        else {
            Node<E> before = getNode(index-1);
            toReturn = before.next.item;
            before.next = before.next.next;
        }
        size--;
        return toReturn;
    }


    // Turns your list into a string
    // Useful for debugging
    public String toString(){
        Node<E> current =  head;
        StringBuilder result = new StringBuilder();
        if(size == 0){
            return "";
        }

        if(size == 1) {
            return head.item.toString();

        }
        else{
            do{
                result.append(current.item);
                result.append(" ==> ");
                current = current.next;
            } while(current != head);
        }
        return result.toString();
    }


    public Iterator<E> iterator() {

        return new ListIterator<E>();

    }

    // provided code for different assignment
    // you should not have to change this
    // change at your own risk!
    // this class is not static because it needs the class it's inside of to survive!
    private class ListIterator<E> implements Iterator<E>{
        Node<E> nextItem;
        Node<E> prev;
        int index;

        @SuppressWarnings("unchecked")
        //Creates a new iterator that starts at the head of the list
        public ListIterator(){
            nextItem = (Node<E>) head;
            index = 0;
        }

        // returns true if there is a next node
        // this is always should return true if the list has something in it
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return size != 0;
        }

        // advances the iterator to the next item
        // handles wrapping around back to the head automatically for you
        public E next() {
            // TODO Auto-generated method stub
            prev =  nextItem;
            nextItem = nextItem.next;
            index =  (index + 1) % size;
            return prev.item;

        }

        // removed the last node was visted by the .next() call
        // for example if we had just created a iterator
        // the following calls would remove the item at index 1 (the second person in the ring)
        // next() next() remove()
        public void remove() {
            int target;
            if(nextItem == head) {
                target = size - 1;
            } else{
                target = index - 1;
                index--;
            }
            CircularLinkedList.this.remove(target); //calls the above class
        }

    }

    // It's easiest if you keep it a singly linked list
    // SO DON'T CHANGE IT UNLESS YOU WANT TO MAKE IT HARDER
    private static class Node<E>{
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }

    }

    public static void main(String[] args){
        CircularLinkedList<Integer> l = new CircularLinkedList<>();
        int n = 5;
        int k = 2;

        for (int i = 1; i < n+1; i++) {
            l.add(i);
        }

        Iterator<Integer> iter = l.iterator();

        // while there is another node
       while(iter.hasNext()) {
            System.out.println(l);
            // skips every element not at count k
            for (int j = 0; j < k; j++) {
                iter.next();
            }
            // removes element at count k
            iter.remove();
       }

    }

}
