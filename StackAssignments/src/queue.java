import java.util.Queue;

public class queue<E> {
    // waiting in line: longest waiter gets served first
        // use breath-first traversal (ensure nodes closer to starting point visited before ones farther away)
    // first in, first out
    /*
    methods
        boolean offer(E item): inserts item to end of queue; return true if successful
        E remove(): removes from front of queue and returns it if queue not empty
            throws NoSuchElementException when queue is empty
        E poll(): removes entry at front of queue and returns it, returns null if queue is empty
        E peek(): returns entry at front of queue without removing it, returns null if queue is empty
        E element(): returns entry at front of queue without removing it
            throws NoSuchElementException when queue is empty

     using single/double linked list:
     - space inefficiencies
     - storage space increased when using linked list due to references stored in nodes
     using an array list:
     - insertion at rear of array is constant time
     - remove from front is linear time o(n)
     - remove from rear of array is constant time
     - insertion at front of array is linear time o(n)

     size = 0;
     capacity = 5;
     public boolean offer(E item) {
        if (size == capacity) {
            reallocate;
        }
        size++;
        rear = (rear + 1) & capacity;
        theData[rear] = item;
        return true;
     }
     public E poll(){
        if(size == 0) {
            return null; }
        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
     */

    // stacks: last in, first out


    Node<E> front;
    Node<E> back;

    public boolean isEmpty(){
        return front == null;
    }

    public E peek(){
        return front.item;
    }

    //removes front of queue
    public E poll(){
        E toReturn = front.item;
        if(front == back) {
            front = null;
            back = null;
        }
        else{
            front = front.next;
        }
        return toReturn;
    }

    //adds to back
    public boolean offer(E item){
        Node<E> temp = new Node<>(item);
        if(this.isEmpty()){
            front = temp;
            back = temp;
        }
        else {
            back.next = temp;
            back = temp;
        }
        return true;
    }

    private static class Node<E>{
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        queue<Integer> q = new queue<>();
        for (int i = 0; i < 10; i++) {
            q.offer(i);
        }

        while(!q.isEmpty()){
            System.out.println(q.poll());
        }
    }
}
