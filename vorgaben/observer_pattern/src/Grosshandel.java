import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class Grosshandel{
    private Logger logger = Logger.getLogger(Grosshandel.class.getName());
    private ConsoleHandler handlerMain = new ConsoleHandler();
    private HashMap<WarenTyp, Integer> lager;
    private List<Einzelhandel> kleinhandel = new ArrayList<>();

    public Grosshandel() {
        lager = new HashMap<>();
        for (WarenTyp typ : WarenTyp.values()) {
            lager.put(typ, 0);
        }
        handlerMain.setFormatter(new LoggerFormatter("Grosshandel"));
        logger.addHandler(handlerMain);
        logger.setUseParentHandlers(false);
    }

    /**
     * Ein Einzelhändler kann Waren mit einer bestimmten Anzahl bestellen. Wenn
     * diese in ausreichender Zahl verfügbar ist, liefert der Großhandel die
     * gewünschte Ware direkt aus.
     *
     * @param kunde   Der Kunde, welcher die Bestellung tätigt.
     * @param auftrag Der Auftrag, welcher abgearbeitet werden soll.
     * @return true, wenn der Auftrag ausgeführt wurde; false sonst
     */
    public boolean bestellen(Einzelhandel kunde, Auftrag auftrag) {
        if (lager.getOrDefault(auftrag.getWarenTyp(), 0) >= auftrag.getAnzahl()) {
            logger.info("Ware: "+auftrag.getWarenTyp()+" "+auftrag.getAnzahl()+" verkauft");
            lager.put(auftrag.getWarenTyp(), lager.get(auftrag.getWarenTyp()) - auftrag.getAnzahl());
            logger.info(lager.get(auftrag.getWarenTyp())+" "+auftrag.getWarenTyp()+" noch vorhanden.");
            kunde.empfangen(auftrag);
            return true;
        }
        return false;
    }

    /**
     * Der Grosshandel bekommt immer Ware, von der am wenigsten aktuell verfügbar
     * ist.
     */
    public void loop() {
        Random random = new Random();
        Map.Entry<WarenTyp, Integer> kleinsterBestand = findeKleinstenBestand();
        int zusatzMenge = random.nextInt(1, 5);
        logger.info(zusatzMenge+" "+kleinsterBestand.getKey()+" zum Lager hinzugefügt.");
        kleinsterBestand.setValue(kleinsterBestand.getValue() + zusatzMenge);
        logger.info(kleinsterBestand.getValue()+" "+kleinsterBestand.getKey()+" vorhanden.");
        notifyObservers(kleinsterBestand);
    }

    private Map.Entry<WarenTyp, Integer> findeKleinstenBestand() {
        Map.Entry<WarenTyp, Integer> kleinsterBestand = null;
        for (Map.Entry<WarenTyp, Integer> loop : lager.entrySet()) {
            if (kleinsterBestand == null || kleinsterBestand.getValue() > loop.getValue()) {
                kleinsterBestand = loop;
            }
        }
        return kleinsterBestand;
    }

    public void addKleinhandel(Einzelhandel kleinhaendler) {
        this.kleinhandel.add(kleinhaendler);
    }

    private void notifyObservers(Map.Entry<WarenTyp, Integer> warenTyp) {
        for(Einzelhandel einzelhandel : kleinhandel){
            einzelhandel.update(warenTyp);
        }
    }
}
