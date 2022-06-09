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
    private int frameCounter;
    private int abilityUsed;
    protected int health;
    protected int manaCost;
    protected int availableAtHeroLevel;
    protected int timeBetweenUsage;
    protected float cooldown;
    private Level currentLevel;

    /** Constructor. Configures loggers for outputting information during gameplay */
    public Abilitys(){
        logger = Logger.getLogger(this.getClass().getName());
        handlerAbilityTree = new ConsoleHandler();
        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }
        frameCounter = 0;
        abilityUsed = 0;

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

    public void abilityUsed(){
        abilityUsed = frameCounter;
    }

    public boolean abilityUsable(MyHero hero){
        if (hero.getLevel() >= getAvailableAtHeroLevel()){
            if(timeBetweenAbility()) {
                if (hero.getMana() >= getManaCost()) {
                    return true;
                } else {
                    logger.info("Du hast nicht genug Mana fuer den " + getName() +
                        ". Du brauchst mindestens " + getManaCost() + ".");
                    return false;
                }
            }else {
                return false;
            }
        }else {
            logger.info("Du musst " + getAvailableAtHeroLevel() + " erreicht haben, um den "
                + getName() + " zu benutzen.");
            return false;
        }
    }

    private boolean timeBetweenAbility(){
        if(timeBetweenUsage*30 <= frameCounter-abilityUsed || abilityUsed == 0){
            return true;
        }else {
            logger.info(((timeBetweenUsage*30)-(frameCounter-abilityUsed))/30+" Sekunden bis die Faehigkeit aktivierbar ist.");
            return false;
        }
    }

    /**
     * Counts every frame
     * */
    public void countFrames(){
        frameCounter++;
    }
}
