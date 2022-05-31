package quest;

import desktop.MyHero;
import item.Items;

public final class QuestType {
    /**
     * enum class
     * */
    public enum Quests{
        KILL,
        LEVEL,
        FIND
    }

    public final Quests questType;
    public final String questName;
    public final String questDescription;
    public final String reward;
    public final int exp;
    public final int remainingKills;
    public final int levelRequirements;
    public final boolean dropItem;

    /**
     * Type Object constructor working with enums and switch cases
     * */
    public QuestType(String questName, MyHero hero, Quests questType){
        this.questType = questType;
        this.questName = questName;


        switch (questType){
            case KILL -> {
                this.remainingKills = hero.getLevel()*2;
                this.levelRequirements = 0;
                this.questDescription = "Erledige "+ remainingKills + " Monster";
                this.exp = (50+hero.getLevel()*hero.getLevel()-(20+hero.getLevel())+50)/4;
                this.reward = "Belohnung: " + exp +" Erfahrungspunkte und 1 unbekannter Gegenstand";
                this.dropItem = false;
            }
            case LEVEL -> {
                this.remainingKills = 0;
                this.levelRequirements = (hero.getLevel()+1);
                this.questDescription = "Erreiche Level " + levelRequirements;
                this.exp = 0;
                this.reward = "Belohnung: 1 unbekannter Gegenstand";
                this.dropItem = true;
            }
            case FIND -> {
                this.remainingKills = 0;
                this.levelRequirements = 0;
                this.questDescription = "Finde die versteckte Schriftrolle";
                this.exp = (50+hero.getLevel()*hero.getLevel()-(20+hero.getLevel())+50)/2;
                this.reward = "Belohnung: " + exp +" Erfahrungspunkte und 1 unbekannter Gegenstand";
                this.dropItem = true;
            }
            default -> {
                this.remainingKills = 0;
                this.levelRequirements = 0;
                questDescription = "";
                this.exp = 0;
                this.reward = "";
                this.dropItem = false;
            }
        }
    }

    /**
     * factory pattern method to call Quest constructor
     * */
    public Quest newQuest(Items item){
        return new Quest(this, item);
    }
}
