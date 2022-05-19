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
    protected int manaCost;
    protected int availableAtLevel;
    private String name;
    private Level currentLevel;

    /** Constructor. Configures loggers for outputting information during gameplay */
    public Spells(){
        logger = Logger.getLogger(this.getClass().getName());
        handlerSpellbook = new ConsoleHandler();
        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }

    }

    /** Getter for the name variable */
    public String getName() {
        return name;
    }

    /** Setter for currentLevel variable */
    public void setLevel(Level level){
        currentLevel = level;
    }

    /** Getter for movement variable */
    public float getMovement(){
        return movement;
    }

    /** Getter for life variable */
    public int getLife(){
        return life;
    }

    /** Getter for manaCost variable */
    public int getManaCost() {
        return manaCost;
    }

    /** Getter for availableAtLevel variable */
    public int getAvailableAtLevel() {
        return availableAtLevel;
    }

    /** Abstract method for activating spell effects */
    public abstract void activateSpellEffect(MyHero myHero);
}
