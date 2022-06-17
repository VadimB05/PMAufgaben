package quest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import logging.InventoryFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
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
        for(Handler handler : log.getHandlers()){
            log.removeHandler(handler);
        }
        log.addHandler(handlerMain);
        log.setUseParentHandlers(false);


    }

    /**
     * iterates through the quest list and logs all quests and their data
     * */
    public void logQuest(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            if(quests.isEmpty()){
                log.info("Keine Quests vorhanden!");
            }else {
                log.info("Meine Quests:");
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

    /**
     * log a quest finished dialog with the quest name
     * */
    public void logQuestFinished(Quest quest){
        log.info("Quest '" + quest.getQuestName() + "' erfolgreich abgeschlossen!");
    }
}
