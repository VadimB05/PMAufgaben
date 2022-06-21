import java.util.logging.Logger;

public class Konto {
    Logger logger = Logger.getLogger(this.getClass().getName());
    private double kontostand = 1000;

    /**
     * Von diesem Konto soll Geld gesendet werden
     *
     * @param betrag Betrag der von diesem Konto abgezogen werden soll
     * @return true wenn der Betrag abgezogen wurde, false wenn nicht
     */
    public boolean sendeGeld(double betrag) {
        while (betrag > kontostand) {
            return false;
        }
        logger.info("Thread: " + Thread.currentThread() +" Kontostand vorher: "+ kontostand);
        kontostand -= betrag;
        logger.info("Thread: " + Thread.currentThread() +" Kontostand nachher: "+ kontostand);
        return true;
    }

    /**
     * Auf dieses Konto soll geld gesendet werden
     *
     * @param betrag Betrag der diesem Konto gutgeschrieben werden soll
     */
    public void empfangeGeld(double betrag) {
        logger.info("Thread: " + Thread.currentThread() +" Kontostand vorher: "+ kontostand);
        kontostand += betrag;
        logger.info("Thread: " + Thread.currentThread() +" Kontostand nachher: "+ kontostand);
    }
}
