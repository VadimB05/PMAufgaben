package ability;

import character.hero.MyHero;

public class PowerUpability extends Abilitys{
    private float strength = 2.0f;



    /** Constructor. Sets mana costs and required hero level */
    public PowerUpability(){
        this.manaCost = 15;
        this.availableAtHeroLevel = 3;
    }

    /** getter for the name */
    @Override
    public String getName() {
        return "Verstaerkung";
    }

    /** Methode for activating Ability to Upgrade the strength of the Hero */
    @Override
    public void activateAbility(MyHero myHero) {
        myHero.addPowerUp(strength);

    }
}
