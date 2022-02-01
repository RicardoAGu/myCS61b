import java.util.Objects;

/**
 * Implementation of double ended queue using array
 * @author RicardoAGu
 */

public class ArrayDeque<YourType> {
    private int size;
    private int arraySize = 8;
    private int pointer = 0;
    private YourType[] myArray;

    /**
     * Creates an empty linked list deque.
     */
    public ArrayDeque() {
        size = 0;
        myArray = (YourType[]) new Object[arraySize];
    }

    /**
     * Resize the array so that its usage factor could be always higher than 25%;
     */
    private void resize() {
        if (((double) size) / arraySize >= 4 & arraySize > 10) {
            YourType[] newArray = (YourType[]) new Object[arraySize / 2];
            System.arraycopy(myArray, 0, newArray, 0, size - (arraySize - pointer) % arraySize);
            System.arraycopy(myArray, pointer, newArray,(arraySize / 2 - (arraySize - pointer) % arraySize)
                    % (arraySize / 2), (arraySize - pointer) % arraySize);
            myArray = newArray;
            pointer = (arraySize / 2 - (arraySize - pointer) % arraySize) % (arraySize / 2);
            arraySize = arraySize / 2;
        }
    }

    /**
     * If the size of array is not large enough, double the array size.
     */
    private void doubleSize() {
        if (size == arraySize) {
            YourType[] newArray = (YourType[]) new Object[2 * arraySize];
            System.arraycopy(myArray, 0, newArray, 0, size - (arraySize - pointer) % arraySize);
            System.arraycopy(myArray, pointer, newArray, (arraySize * 2 - (arraySize - pointer) % arraySize)
                    % (arraySize * 2), (arraySize - pointer) % arraySize);
            myArray = newArray;
            pointer = (arraySize * 2 - (arraySize - pointer) % arraySize) % (arraySize * 2);
            arraySize = 2 * arraySize;
        }
    }

    /**
     * Adds an item of YourType to the front of the deque.
     */
    public void addFirst(YourType item) {
        doubleSize();
        resize();
        pointer = (pointer + arraySize - 1) % arraySize;
        myArray[pointer] = item;
        size += 1;
    }

    /**
     * Adds an item of YourType to the back of the deque.
     */
    public void addLast(YourType item) {
        doubleSize();
        resize();
        myArray[size - (arraySize - pointer) % arraySize] = item;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
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
    public YourType removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            YourType it = myArray[pointer];
            myArray[pointer] = null;
            pointer = (pointer + 1) % arraySize;
            size += -1;
            return it;
        }
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public YourType removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            YourType it = myArray[size - (arraySize - pointer) % arraySize - 1];
            myArray[size - (arraySize - pointer) % arraySize - 1] = null;
            size += -1;
            return it;
        }
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * @param index The index of expected item.
     */
    public YourType get(int index) {
        return myArray[(pointer + index) % arraySize];
    }
}
