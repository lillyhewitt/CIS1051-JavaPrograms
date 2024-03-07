public class Stack<E> {
    private Node<E> top;

    //REMOVING HEAD
    public E pop() {
        E toReturn = top.item;
        top = top.next;

        return toReturn;
    }

    public void push(E item) {
        Node<E> adding = new Node<>(item);
        adding.next = top;
        top = adding;
    }

    // see first item
    public E peek() {
        return top.item;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public static class Node<E>{
        Node<E> next;
        E item;
        public Node(E item) {
            this.item = item;
        }
    }
}
