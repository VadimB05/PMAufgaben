import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class Einzelhandel implements Observer<Map.Entry<WarenTyp, Integer>>{
    private Logger logger = Logger.getLogger(Einzelhandel.class.getName());
    private HashMap<WarenTyp, Integer> lager;
    private Grosshandel grosshandel;
    private List<Auftrag> auftraege;


    public Einzelhandel(Grosshandel grosshandel) {
        lager = new HashMap<>();
        auftraege = new ArrayList<>();
        this.grosshandel = grosshandel;
        grosshandel.addKleinhandel(this);
    }

    /**
     * Kunde bestellt Ware: Speichere den Auftrag ab und versuche später
     * in <code>loop()</code>, den Auftrag beim Großhandel zu bestellen.
     *
     * @param auftrag der Auftrag, der aufgenommen werden soll.
     */
    public void bestellen(Auftrag auftrag) {
        logger.info(this+" Neuer Auftrag: "+auftrag.getAnzahl()+" "+auftrag.getWarenTyp());
        auftraege.add(auftrag);
    }

    /**
     * Empfange Ware vom Großhandel, füge die Ware dem Lager hinzu und
     * entferne den offenen Auftrag.
     *
     * @param auftrag der Auftrag, der abgearbeitet werden soll.
     */
    public void empfangen(Auftrag auftrag) {
        logger.info(this + " " + auftrag.getAnzahl()+" "+auftrag.getWarenTyp()+ " empfangen.");
        lager.put(auftrag.getWarenTyp(), lager.getOrDefault(auftrag.getWarenTyp(), 0) + auftrag.getAnzahl());
        auftraege.remove(auftrag);
        logger.info(this + " Aufträge vorhanden: "+auftraege.size());
    }

    /**
     * Versuche alle Aufträge durch Bestellung beim Großhandel abzuschließen.
     */
    public void loop() {
    }

    /**
     * Existieren beim Einzelhändler noch offene Aufträge?
     *
     * @return true wenn mehr als 1 Auftrag offen ist, sonst false
     */
    public boolean hatAuftraege() {
        return auftraege.size() > 0;
    }

    @Override
    public void update(Map.Entry<WarenTyp, Integer> warenTyp) {
        logger.info(this+" Ware: "+warenTyp.getValue()+" "+warenTyp.getKey()+" im Großhandel vorhanden.");
        loop();
        checkAuftraege(warenTyp.getKey(),warenTyp.getValue());
    }

    /**
     * Hat der Grosshändler genug Waren, damit wir unsere Aufträge bestellen können?
     * */
    public void checkAuftraege(WarenTyp warenTyp, int anzahl){
        for (int i = 0; i < auftraege.size(); i++) {
            if(warenTyp.equals(auftraege.get(i).getWarenTyp())
            && anzahl >= auftraege.get(i).getAnzahl()){
                wareBestellen(i);
            }
        }
    }

    /**
     * Bestelle die Ware beim Grosshändler
     * */
    private void wareBestellen(int position) {
        logger.info(this + " "+ auftraege.get(position).getAnzahl() +
            " " + auftraege.get(position).getWarenTyp() + " beim Grosshändler kaufen.");
        grosshandel.bestellen(this, auftraege.get(position));
    }
}
