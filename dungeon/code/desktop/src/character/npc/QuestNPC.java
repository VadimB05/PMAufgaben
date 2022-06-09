package character.npc;

import character.Character;
import character.hero.MyHero;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import quest.Quest;

import java.util.ArrayList;
import java.util.List;

public class QuestNPC extends Character {
    public boolean logged;
    private List<Quest> questList;
    /**
     * Must be implemented for all objects that should be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */
    public QuestNPC(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        idleAnimationRightList.add("character/npc/zombie_idle_anim_f0.png");
        idleAnimationRightList.add("character/npc/zombie_idle_anim_f1.png");
        idleAnimationRightList.add("character/npc/zombie_idle_anim_f2.png");
        idleAnimationRightList.add("character/npc/zombie_idle_anim_f3.png");
        animation = new Animation(idleAnimationRightList,8);
        setLogged(false);
        questList = new ArrayList<>();
    }

    /**
     * Sets character on random position
     *
     * @param level     Current level
     */
    public void setLevel(Level level){
        currentLevel = level;
        position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
    }

    /**
     * Checks if our hero is colliding with questNPC
     * */
    public boolean doesCollide(MyHero hero){
        return hero.getHitBox().overlaps(getHitBox());
    }

    public void showQuests(){
        allQuests();
        setLogged(true);
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * return all the quests the NPC has for us
     * */
    private void allQuests(){
        if(questList.isEmpty()){
            logger.info("Keine neuen Quests vorhanden!");
        }else {
            for (Quest quest : questList)
                logger.info("\n"
                    +quest.getQuestName() + ":\n"
                    + quest.getQuestDescription() + "\n"
                    + quest.getReward() + "\n");
            logger.info("Druecke F um Quests anzunehmen, ignoriere um abzulehnen!");
        }
    }

    /**
     * clear the list of quests the NPC has for us
     * */
    public void questsAccepted(){
        questList.clear();
    }

    public void addToQuestList(Quest quest){
        questList.add(quest);
    }
}

