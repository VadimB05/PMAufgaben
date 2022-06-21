import java.util.*;
import java.util.logging.Logger;

public class Kunde implements Runnable{
    Logger logger = Logger.getLogger(this.getClass().getName());
    private Konto konto;
    private Queue<Rechnung> offeneRechnungen;

    /** Erstelle einen neune Kunden, der sich ein Konto bei der Bank erstellt. */
    public Kunde() {
        konto = new Konto();
        offeneRechnungen = new LinkedList<>();
    }

    /**
     * Empfange eine Rechnung zum bezahlen.
     * Benachrichtigt den wartenden Thread
     *
     * @param rechnung Die Rechnung.
     */
    public void empfangeRechnung(Rechnung rechnung) {
        offeneRechnungen.add(rechnung);
        synchronized (this) {
            this.notify();
        }
    }

    public Konto getKonto() {
        return konto;
    }

    /**
     * einseitige Synchronisation zum bezahlen von Rechnungen, falls welche vorhanden sind.
     * */
    public synchronized void bezahleRechnung() {
        while (offeneRechnungen.peek() == null) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        logger.info("Ueberweisung gestartet von Kunde: " + this);
        Bank.ueberweisen(konto, offeneRechnungen.poll());
    }

    @Override
    public void run() {
        bezahleRechnung();
    }
}
