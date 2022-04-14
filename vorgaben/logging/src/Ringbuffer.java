import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Ringbuffer for String objects.
 */
public class Ringbuffer {
    private final String[] buffer;
    private int start, elements;

    private static Logger logger = Logger.getLogger(Ringbuffer.class.getName());


    /**
     * Constructor for the buffer which creates the String array for the storage.
     *
     * @param size needs to be at least 1
     * @throws IllegalArgumentException when the size is below or equal to 0
     */
    public Ringbuffer(int size) {

        FileHandler loggedData;
        ConsoleHandler handlerRingbuffer = new ConsoleHandler();
        handlerRingbuffer.setLevel(Level.INFO);
        handlerRingbuffer.setFormatter(new SecondFormatter("RingbufferLogger"));
        logger.setLevel(Level.INFO);
        logger.addHandler(handlerRingbuffer);
        logger.setUseParentHandlers(false);
        try {
            loggedData = new FileHandler("data/dataText.txt"); //append can be set true, if you want to keep old saved data
            loggedData.setFormatter(new SecondFormatter("RingbufferLogger"));
            loggedData.setLevel(Level.INFO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(loggedData);


        if (size <= 0) {
            logger.warning("The Size of the buffer needs to be at least 1.");
            throw new IllegalArgumentException("The Size of the buffer needs to be at least 1.");
        }
        logger.info("Creating array with size of " + size + " for storage.");
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
            logger.warning("The Current Buffer is already full.");
            throw new IllegalStateException("The Current Buffer is already full.");
        }
        logger.info("Adding " + element + " to buffer on position " + (start + elements) % buffer.length);
        buffer[(start + elements) % buffer.length] = element;
        logger.info("Increasing Element count by 1 to " + (elements + 1));
        ++elements;
    }

    /**
     * Removes the first Element at the position of the storage marker.
     *
     * @throws IllegalStateException when the method is called and there is no element available.
     * @return the removed Element.
     */
    public String remove() {
        logger.info("Currently the buffer does contain: " + elements + " elements");
        if (elements == 0) {
            logger.warning("The Current Buffer does not contain any element.");
            throw new IllegalStateException("The Current Buffer does not contain any element.");
        }
        String s = buffer[start];
        logger.info("Moving element from buffer to temporary variable the value: " + s);
        logger.info("Moving the start pointer from " + start + " to " + (start + 1) % buffer.length);
        start = (start + 1) % buffer.length;
        logger.info("Decreasing Element count by 1 to " + (elements - 1));
        elements--;
        return s;
    }

    /**
     * Gives the number of how much space is still available to be filled.
     *
     * @return the number of empty Spaces
     */
    public int emptySpace() {
        logger.info("Method emptySpace current elementCount is " + elements + " elements and the buffer has a size of " + buffer.length + ".");
        return buffer.length - elements;
    }

    /**
     * Gives the amount of elements currently held in the buffer
     *
     * @return the amount of elements currently held in the buffer
     */
    public int elementsCount() {
        logger.info("Method elementsCount current count is " + elements + " elements.");
        return elements;
    }
}
