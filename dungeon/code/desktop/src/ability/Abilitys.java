package ability;

import character.hero.MyHero;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Abilitys {
    Logger logger;
    ConsoleHandler handlerAbilityTree;
    private String name;
    protected int health;
    protected int manaCost;
    protected int availableAtHeroLevel;
    protected float cooldown;
    private Level currentLevel;

    /** Constructor. Configures loggers for outputting information during gameplay */
    public Abilitys(){
        logger = Logger.getLogger(this.getClass().getName());
        handlerAbilityTree = new ConsoleHandler();
        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }

    }

    /** Getter for the name variable */
    public String getName(){
        return name;
    }

    /** Setter for currentLevel variable */
    public void setLevel(Level level){
        currentLevel = level;
    }

    public int getHealth(){
        return health;
    }

    /** Getter for manaCost variable */
    public int getManaCost(){
        return manaCost;
    }

    /** Getter for cooldown variable */
    public float getCooldown(){
        return cooldown;
    }


    /** Getter for availableAtHeroLevel variable */
    public int getAvailableAtHeroLevel(){
        return availableAtHeroLevel;
    }

    /** Abstract method for activating spell effects */
    public abstract void activateAbility(MyHero myHero);
}
