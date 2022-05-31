package drucker;
import java.util.logging.Logger;

public abstract class Drucker {
    Logger logger = Logger.getLogger(Drucker.class.getName());

    public final void kopieren() {
        scannen();
        drucken();
    }

    protected abstract void scannen();
    protected abstract void drucken();
}
