package drucker;

public class Laserdrucker extends Drucker {

    @Override
    protected void scannen() {
        logger.info("Scanne das Dokument mit dem Laserdrucker");
    }

    @Override
    protected void drucken() {
        logger.info("Drucke das Dokument mit dem Laserdrucker");
    }
}
