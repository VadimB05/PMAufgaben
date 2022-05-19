package magic;

import logging.InventoryFormatter;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import magic.*;

public class Spellbook {
    Logger logger;
    ConsoleHandler handlerSpellbook;
    ArrayList<Spells> spellbookList;

    public Spellbook() {
        logger = Logger.getLogger(this.getClass().getName());
        handlerSpellbook = new ConsoleHandler();
        spellbookList = new ArrayList<>();
        addSpellsToSpellbook();

        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }
        handlerSpellbook.setLevel(Level.INFO);
        handlerSpellbook.setFormatter(new InventoryFormatter("Spellbook Logger"));
        logger.setLevel(Level.INFO);
        logger.addHandler(handlerSpellbook);
        logger.setUseParentHandlers(false);

    }

    public void addSpellsToSpellbook(){
        spellbookList.add(new MovementSpell());
        spellbookList.add(new LifeSpell());
    }

    /** Show what is in the Spellbook. */
    public void showSpellbook() {
        StringBuilder spellbookSpells = new StringBuilder();
        spellbookSpells.append("Das Zauberbuch: ");
        if(!spellbookList.isEmpty()) {
            for (int i = 0; i < spellbookList.size(); i++) {
                if (i >= 1) {
                    spellbookSpells.append(",");
                }

                spellbookSpells.append(" ").append(spellbookList.get(i).
                    getName()).append(" (").append(i + 1).append(" zum Aktivieren und ");
            }
            logger.info(spellbookSpells.toString());
        } else {
            logger.info("Fehler");
        }
    }

    public ArrayList<Spells> getSpellbookArrayList(){
        return spellbookList;
    }
}
