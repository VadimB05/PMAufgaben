package ability;

import character.hero.MyHero;
import character.monster.Monster;

public class Healability extends Abilitys{
    private int health = 80;


    /** Constructor. Sets mana costs and required hero level */
    public Healability() {
        this.manaCost = 10;
        this.availableAtHeroLevel = 2;
        timeBetweenUsage = 60;

    }

    /** getter for the name */
    @Override
    public String getName() {
        return "Heiligergeist";
    }

    /** Methode for activating Ability for Health regeneration  */
    @Override
    public void activateAbility(MyHero myHero) {
        if(myHero.getMaxHealth()-myHero.getHealth()>health){
            myHero.addHealth(health);
        }
        else{
            myHero.addHealth(myHero.getMaxHealth()-myHero.getHealth());
        }
    }

    @Override
    public void activateAbility(Monster monster) {

    }
}
