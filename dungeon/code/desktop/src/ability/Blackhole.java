package ability;

import character.hero.MyHero;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Blackhole extends Abilitys {
    private final Rectangle hitBox;
    private long lastCastTime;
    protected int damage = 100;

    /** Constructor.  required hero level */
    public Blackhole() {
        this.availableAtHeroLevel = 4;
        hitBox = new Rectangle();
        timeBetweenUsage = 30;
    }

    /** getter for the name */
    @Override
    public String getName() {
        return "Schwarzesloch";
    }

    /** Methode for activating Ability not finished  */
    @Override
    public void activateAbility(MyHero myHero) {


    }
}
