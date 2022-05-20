package quest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import logging.InventoryFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestLog {
    private List<Quest> quests = new ArrayList<>();
    Logger log = Logger.getLogger(this.getClass().getName());
    ConsoleHandler handlerMain;

    public QuestLog(){
        handlerMain = new ConsoleHandler();

        handlerMain.setLevel(Level.INFO);
        handlerMain.setFormatter(new InventoryFormatter("Main Logger"));
        log.setLevel(Level.INFO);
        log.addHandler(handlerMain);
        log.setUseParentHandlers(false);
    }

    public void logQuest(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            if(quests.isEmpty()){
                log.info("Keine Quests vorhanden!");
                log.info("Druecke O um Quests anzunehmen, ignoriere um abzulehnen!");
            }else {
                for (Quest quest : quests)
                    log.info("\n"
                            +quest.getQuestName() + ":\n"
                            + quest.getQuestDescription() + "\n"
                            + quest.getReward() + "\n");
            }
        }
    }

    public void addQuest(Quest quest){
        quests.add(quest);
    }

    public void removeQuest(Quest quest){
        quests.remove(quest);
    }

    public void logQuestFinished(Quest quest){
        log.info("Quest '" + quest.getQuestName() + "' erfolgreich abgeschlossen!");
    }
}
