import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bank {

    /**
     * erstellt einen Transaktions Thread und startet diesen.
     *
     * @param von Konto von dem das Geld gesendet wird
     * @param rechnung Die Rechnung die bezahlt werden muss
     */
    public static void ueberweisen(Konto von, Rechnung rechnung) {
        Transaktion transaktion = new Transaktion(von, rechnung);

        ExecutorService pool = Executors.newCachedThreadPool();

        pool.execute(transaktion);
    }
}
