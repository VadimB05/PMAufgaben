package quest;

import controller.EntityController;
import desktop.MyHero;
import item.Items;
import observer.QuestObserver;

public class Quest implements QuestObserver {
    private final QuestType type;
    private final String questName;
    private String questDescription;
    private final String reward;
    private final int exp;
    private final boolean dropItem;
    private boolean questAccepted;
    private int remainingKills;
    private int levelRequirements;
    private QuestLog questLog;
    private MyHero hero;
    private Items item;
    private EntityController entityController;

    /**Constructor Quest, Type Object Pattern*/
    public Quest(QuestType type, Items item){
        this.type = type;
        this.questName = type.questName;
        this.questDescription = type.questDescription;
        this.exp = type.exp;
        this.dropItem = type.dropItem;
        this.questAccepted = false;
        this.reward = type.reward;
        this.remainingKills = type.remainingKills;
        this.levelRequirements = type.levelRequirements;
        this.item = item;
    }

    public String getQuestName() {
        return questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public String getReward() {
        return reward;
    }

    public int getLevelRequirements() {
        return levelRequirements;
    }

    public boolean isQuestAccepted() {
        return questAccepted;
    }

    public void setQuestAccepted(QuestLog questlog,
                                 MyHero hero,
                                 EntityController entityController) {
        this.hero = hero;
        this.questLog = questlog;
        this.questAccepted = true;
        this.entityController = entityController;
        questlog.addQuest(this);
    }

    /**
     * Finishes the quests, distributes the rewards and calls QuestLog methods to log some data
     * */
    @Override
    public void update() {
        switch (type.questType){
            case FIND -> {
                questLog.logQuestFinished(this);
                questLog.removeQuest(this);
                dropItem();
                hero.gainExp(exp);
            }
            case LEVEL -> {
                questLog.logQuestFinished(this);
                questLog.removeQuest(this);
                dropItem();
            }
            case KILL -> {
                remainingKills--;
                this.questDescription = "Erledige "+ remainingKills + " Monster";
                if(remainingKills==0){
                    questLog.logQuestFinished(this);
                    questLog.removeQuest(this);
                    dropItem();
                    hero.gainExp(exp);
                }
            }
        }

    }

    /**
     * Drops the reward item in front of the hero
     * */
    private void dropItem() {
        if(dropItem){
            entityController.add(item);
            item.setPosition(hero.getPosition());
            item.setPickedUp(false);
        }
    }
}
