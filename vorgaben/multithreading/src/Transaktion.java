/**
 * Eine Transaktion bearbeitet eine Rechnung
 *
 * @param von Konto von dem aus die Rechnung bezahlt werden soll
 * @param rechnung Die zu bezahlende Rechnung
 */
public record Transaktion(Konto von, Rechnung rechnung) implements Runnable{

    /**
     * mehrseitige Synchronisation zum versenden von Geld
     * */
    @Override
    public void run() {
        synchronized (von) {
            if(von.sendeGeld(rechnung.betrag())){
                rechnung.empfaenger().empfangeGeld(rechnung().betrag());
            }
        }
    }
}
