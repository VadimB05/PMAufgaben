package magic;

import logging.InventoryFormatter;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spellbook {
    Logger logger;
    ConsoleHandler handlerSpellbook;
    ArrayList<Spells> spellbookList;

    /** Constructor. Sets loggers for outputting information and adds spells to the spellbook */
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

    /** Adds spells to the spellbook on creation */
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
}
