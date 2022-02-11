/**
 * Implementation of double ended queue using array
 * @author RicardoAGu
 */

public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private int arraySize = 8;
    private int pointer = 0;
    private T[] myArray;

    /**
     * Creates an empty linked list deque.
     */
    public ArrayDeque() {
        size = 0;
        myArray = (T[]) new Object[arraySize];
    }

    /**
     * Resize the array so that its usage factor could be always higher than 25%;
     */
    private void resize() {
        if (((double) size) / arraySize <= 0.25 & arraySize > 10) {
            T[] newArray = (T[]) new Object[arraySize / 2];
            if (pointer + size > arraySize) {
                System.arraycopy(myArray, pointer, newArray, 0, arraySize - pointer);
                System.arraycopy(myArray, 0, newArray, arraySize - pointer,
                        pointer + size - arraySize);
            } else {
                System.arraycopy(myArray, pointer, newArray, 0, size);
            }
            myArray = newArray;
            pointer = 0;
            arraySize = arraySize / 2;
        }
    }

    /**
     * If the size of array is not large enough, double the array size.
     */
    private void doubleSize() {
        if (size == arraySize) {
            T[] newArray = (T[]) new Object[2 * arraySize];
            if (pointer + size > arraySize) {
                System.arraycopy(myArray, pointer, newArray, 0, arraySize - pointer);
                System.arraycopy(myArray, 0, newArray, arraySize - pointer,
                        pointer + size - arraySize);
            } else {
                System.arraycopy(myArray, pointer, newArray, 0, size);
            }
            myArray = newArray;
            pointer = 0;
            arraySize = 2 * arraySize;
        }
    }

    /**
     * Adds an item of T to the front of the deque.
     */
    @Override
    public void addFirst(T item) {
        doubleSize();
        pointer = (pointer + arraySize - 1) % arraySize;
        myArray[pointer] = item;
        size += 1;
    }

    /**
     * Adds an item of T to the back of the deque.
     */
    @Override
    public void addLast(T item) {
        doubleSize();
        myArray[(pointer + size) % arraySize] = item;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
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
        int index = pointer;
        for (int i = 0; i < size; i++) {
            System.out.print(myArray[(pointer + i) % arraySize]);
            System.out.print(' ');
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
            T it = myArray[pointer];
            myArray[pointer] = null;
            pointer = (pointer + 1) % arraySize;
            size += -1;
            resize();
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
            T it = myArray[(pointer + size - 1) % arraySize];
            myArray[(pointer + size - 1) % arraySize] = null;
            size += -1;
            resize();
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
        return myArray[(pointer + index) % arraySize];
    }
}
