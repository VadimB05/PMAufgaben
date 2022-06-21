import java.util.List;

import static java.lang.Thread.sleep;

public class Geldeintreiber implements Runnable{
    Kunde auftraggeber;
    List<Kunde> kunden;

    /**
     * Erstelle einen neuen Geleintreiber, der einen Auftraggeber und eine Liste von Kunden übergeben bekommt
     *
     * @param auftraggeber, Kunde der das Geld der Rechnung erhält
     * @param kunden, Liste der Kunden, die dem auftraggeber Geld überweisen müssen
     * */
    public Geldeintreiber(Kunde auftraggeber, List<Kunde> kunden){
        this.auftraggeber = auftraggeber;
        this.kunden = kunden;
    }

    /**
     * Erstelle eine neue Rechnung und übersende diese dem Kunden.
     * Warte 5 sek nach der Versendung der Rechnung.
     * */
    @Override
    public void run() {
        for(Kunde k : kunden){
            k.empfangeRechnung(new Rechnung(10,auftraggeber.getKonto()));
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Notified");
            }
        }
    }
}
