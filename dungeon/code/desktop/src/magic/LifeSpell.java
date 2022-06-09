package magic;

import character.hero.MyHero;

public class LifeSpell extends Spells {
    private int life = 40;
    private String name = "Lifespell";

    /** Constructor. Sets mana costs and required dungeon level */
    public LifeSpell() {
        this.manaCost = 5;
        this.availableAtLevel = 2;
    }

    @Override
    public String getName() {
        return "Heilenungspruch";
    }

    @Override
    public void activateSpellEffect(MyHero myHero) {
        if(myHero.getMaxHealth()-myHero.getHealth()>life){
            myHero.addHealth(life);
        }
        else{
            myHero.addHealth(myHero.getMaxHealth()-myHero.getHealth());
        }
    }
}
