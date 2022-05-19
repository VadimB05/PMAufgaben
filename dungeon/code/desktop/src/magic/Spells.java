package magic;

import com.badlogic.gdx.utils.TimeUtils;
import desktop.MyHero;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Spells {
    Logger logger;
    ConsoleHandler handlerSpellbook;
    protected float movement;
    protected int life;
    private long cooldown;
    private String name;
    private Level currentLevel;


    public Spells(){
        logger = Logger.getLogger(this.getClass().getName());
        handlerSpellbook = new ConsoleHandler();
        cooldown = TimeUtils.millis();



        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }

    }
    public String getName() {
        return name;
    }
    public void setLevel(Level level){
        currentLevel = level;
    }
    public float getMovement(){
        return movement;
    }
    public int getLife(){
        return life;
    }
    public abstract void castSpell(MyHero myHero);

//    public void getSpellbook() {
//        StringBuilder spellbooks = new StringBuilder();
//        spellbooks.append("Zauberbuch: ");
//        if(!spellbook.isEmpty()){
//            for(int i=0;i<spellbook.size();i++){
//
//            }
//        }
//
//    }

//    public boolean setCooldown(){
//        if(TimeUtils.millis() - cooldown > 15000){
//            return true;
//        }
//        return false;
//    }
}
