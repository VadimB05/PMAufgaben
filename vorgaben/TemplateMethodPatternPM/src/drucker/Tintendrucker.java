package drucker;

public class Tintendrucker extends Drucker {

    @Override
    protected void scannen() {
        logger.info("Scanne das Dokument mit dem Tintendrucker");
    }

    @Override
    protected void drucken() {
        logger.info("Drucke das Dokument mit dem Tintendrucker");
    }
}
