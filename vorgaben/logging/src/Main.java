import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        ConsoleHandler handlerMain = new ConsoleHandler();
        handlerMain.setLevel(Level.INFO);
        handlerMain.setFormatter(new FirstFormatter("MainLogger"));
        logger.setLevel(Level.INFO);
        logger.addHandler(handlerMain);
        logger.setUseParentHandlers(false);


        Ringbuffer buffer = new Ringbuffer(5);
        try {
            buffer.remove();
        } catch (IllegalStateException stateException) {
            logger.warning(stateException.getMessage());
        }
        buffer.add("First");
        logger.info("First element added succesfully");
        buffer.add("Second");
        logger.info("Second element added Succesfully");
        try {
            buffer.add("Third");
            logger.info("Third element added Succesfully");
        } catch (IllegalStateException stateException) {
            logger.warning(stateException.getMessage());
        }
        String first = buffer.remove();
        logger.info("The removed element is: "+ first);
    }
}
