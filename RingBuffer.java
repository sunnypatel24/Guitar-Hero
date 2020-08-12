import java.util.NoSuchElementException;

// A class to represent a ring buffer data structure.
public class RingBuffer {
    private static final String EMPTY_BUFFER = "[]";

    private double bufferQueue[];
    private int capacity;
    private int first;
    private int last;
    private int currentSize;

    /* Constructor that initializes the double array to represent the ring buffer, along with first
     * and last indices and currentSize holder.
     * @param capacity The maximum size that the ring buffer is initialized to.
    */
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.bufferQueue = new double[capacity];
        this.first = 0;
        this.last = 0;
        this.currentSize = 0;
    }


    /* return number of items currently in the buffer
     * @return currentSize Holds the amount of values that are currently in the buffer.
    */
    public int size() {
        return currentSize;
    }


    /* Checks if the buffer is empty (size equals zero).
     * @return Returns true if currentSize is 0, and false if greater than 0.
    */
    public boolean isEmpty() {
        return (currentSize == 0);
    }


    /* Checks if the buffer is full (size equals capacity).
     * @return Returns true if the currentSize is equal to the maximum capacity, and false if it is
     * less.
    */
    public boolean isFull() {
        return (currentSize == capacity);
    }


    /* Adds the value x to the end of the buffer (as long as the buffer is not full).
     * @param x Double value that is to be put into the queue of the buffer (at index last).
    */
    public void enqueue(double x) throws IllegalStateException {
        boolean currentlyFull = isFull();
        if (!currentlyFull) {
            bufferQueue[last] = x;
            currentSize++;
            last++;
            if (last == capacity) {
                last = 0;
            }
        } else {
            throw new IllegalStateException();
        }
    }


    /* Deletes and returns the value from the front of the buffer (as long as the buffer is not
     * empty).
     * @return firstNum The value that was removed from the queue.
    */
    public double dequeue() throws NoSuchElementException {
        boolean currentlyEmpty = isEmpty();
        double firstNum = bufferQueue[first];
        if (!currentlyEmpty) {
            first++;
            currentSize--;
            if (first == capacity) {
                first = 0;
            }
            return firstNum;
        } else {
            throw new NoSuchElementException();
        }
    }


    /* Returns (but does not delete) the value from the front of the buffer.
     * @return Returns the value that is at index first in the buffer.
    */
    public double peek() throws NoSuchElementException {
        boolean currentlyEmpty = isEmpty();
        if (!currentlyEmpty) {
            return bufferQueue[first];
        } else {
            throw new NoSuchElementException();
        }
        
    }


    /* Returns a String of the form [front, next, next, last] (all of the values from first
     * (inclusive) to last (exclusive)).
     * @return queueOrder A string containing the order of the values currently in queue in the
     *  buffer.
     * @return EMPTY_BUFFER A string of empty brackets to indicate that there is no value currently
     *  in queue.
    */
    public String toString() {
        if (currentSize == 0) {
            return EMPTY_BUFFER;
        } else {
            String queueOrder = "";
            queueOrder += "[" + bufferQueue[first];
            for (int bufferIndex = first + 1; bufferIndex < first + currentSize; bufferIndex++) {
                queueOrder += ", ";
                queueOrder += bufferQueue[bufferIndex % capacity];
            }
            queueOrder += "]";
            return queueOrder;
        }
    }

}