package magic;

import desktop.MyHero;

public class LifeSpell extends Spells {
    private int life = 40;
    private String name = "Lifespell";

    public LifeSpell() {

    }

    @Override
    public String getName() {
        return "Heilenungspruch";
    }

    @Override
    public void castSpell(MyHero myHero) {
        if(myHero.getMaxHealth()-myHero.getHealth()>life){
            myHero.addHealth(life);
        }
        else{
            myHero.addHealth(myHero.getMaxHealth()-myHero.getHealth());
        }
    }
}
