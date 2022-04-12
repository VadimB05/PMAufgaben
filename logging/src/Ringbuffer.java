
/**
 * Simple Ringbuffer for String objects.
 */
public class Ringbuffer {
    private final String[] buffer;
    private int start, elements;

    /**
     * Constructor for the buffer which creates the String array for the storage.
     *
     * @param size needs to be at least 1
     * @throws IllegalArgumentException when the size is below or equal to 0
     */
    public Ringbuffer(int size) {
        if (size <= 0) {
            System.err.println("The Size of the buffer needs to be at least 1.");
            throw new IllegalArgumentException("The Size of the buffer needs to be at least 1.");
        }
        System.out.println("Creating array with size of " + size + " for storage.");
        buffer = new String[size];
    }

    /**
     * Adds an Element at the position of the storage marker.
     *
     * @param element any String element which should be buffered.
     * @throws IllegalStateException when the method is called and there is no empty space available.
     */
    public void add(String element) {
        if (elements == buffer.length) {
            System.err.println("The Current Buffer is already full.");
            throw new IllegalStateException("The Current Buffer is already full.");
        }
        System.out.println("Adding " + element + " to buffer on position " + (start + elements) % buffer.length);
        buffer[(start + elements) % buffer.length] = element;
        System.out.println("Increasing Element count by 1 to " + (elements + 1));
        ++elements;
    }

    /**
     * Removes the first Element at the position of the storage marker.
     *
     * @throws IllegalStateException when the method is called and there is no element available.
     * @return the removed Element.
     */
    public String remove() {
        System.out.println("Currently the buffer does contain: " + elements + " elements");
        if (elements == 0) {
            System.err.println("The Current Buffer does not contain any element.");
            throw new IllegalStateException("The Current Buffer does not contain any element.");
        }
        String s = buffer[start];
        System.out.println("Moving element from buffer to temporary variable the value: " + s);
        System.out.println("Moving the start pointer from " + start + " to " + (start + 1) % buffer.length);
        start = (start + 1) % buffer.length;
        System.out.println("Decreasing Element count by 1 to " + (elements - 1));
        elements--;
        return s;
    }

    /**
     * Gives the number of how much space is still available to be filled.
     *
     * @return the number of empty Spaces
     */
    public int emptySpace() {
        System.out.println("Method emptySpace current elementCount is " + elements + " elements and the buffer has a size of " + buffer.length + ".");
        return buffer.length - elements;
    }

    /**
     * Gives the amount of elements currently held in the buffer
     *
     * @return the amount of elements currently held in the buffer
     */
    public int elementsCount() {
        System.out.println("Method elementsCount current count is " + elements + " elements.");
        return elements;
    }
}
