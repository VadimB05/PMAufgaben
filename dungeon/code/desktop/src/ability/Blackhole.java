package ability;

import character.hero.MyHero;
import character.monster.Monster;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Blackhole extends Abilitys {
    private final Rectangle hitBox;
    private long lastCastTime;
    protected int damage = 100;

    /** Constructor.  required hero level */
    public Blackhole() {
        this.availableAtHeroLevel = 1;
        hitBox = new Rectangle();
        timeBetweenUsage = 30;
    }

    /** getter for the name */
    @Override
    public String getName() {
        return "Schwarzesloch";
    }

    @Override
    public void activateAbility(MyHero myHero) {
    }

    /** Methode for activating Ability */
    @Override
    public void activateAbility(Monster monster) {
    monster.getDamaged(damage);
    }
}
