import java.util.Objects;

/**
 * Implementation of double ended queue using double linked lists
 * @author RicardoAGu
 */

public class LinkedListDeque<T> implements Deque<T>{
    /**
     * Define the every single unit used in our lists.
     */
    private class Node {
        T item;
        Node next;
        Node prev;
        Node(T it, Node ne, Node pr) {
            item = it;
            next = ne;
            prev = pr;
        }

        /**
         * help build recursion of nodes.
         */
        private T recursion(int index) {
            if (index == 0) {
                return item;
            } else {
                return next.recursion(index - 1);
            }
        }
    }

    private int size;
    private Node sentinel;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        size = 0;
        T n = (T) new Object();
        sentinel = new Node(n, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Adds an item of T to the front of the deque.
     */
    @Override
    public void addFirst(T item) {
        Node n = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = n;
        sentinel.next = n;
        size += 1;
    }

    /**
     * Adds an item of T to the back of the deque.
     */
    @Override
    public void addLast(T item) {
        Node n = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = n;
        sentinel.prev = n;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return Objects.equals(sentinel.next, sentinel);
    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        Node n = sentinel;
        while (!Objects.equals(n.next, sentinel)) {
            System.out.print(n.next.item);
            System.out.print(' ');
            n = n.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T it = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size += -1;
            return it;
        }
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T it = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size += -1;
            return it;
        }
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * @param index The index of expected item.
     */
    @Override
    public T get(int index) {
        Node n = sentinel;
        for (int i = 0; i <= index; i++) {
            n = n.next;
        }
        return n.item;
    }

    /**
     * Gets the item at the given index using recursion, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null.
     * @param index The index of expected item.
     */
    public T getRecursive(int index) {
        return sentinel.next.recursion(index);
    }
}
