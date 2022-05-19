package magic;


import desktop.MyHero;

public class MovementSpell extends Spells {
    private float movementspeed = 0.1f;
    private String name = "Movementspell";

    /** Constructor. Sets mana costs and required dungeon level */
    public MovementSpell() {
        this.manaCost = 10;
        this.availableAtLevel = 3;
    }

    @Override
    public String getName() {
        return "Geschwindigkeitsspruch";
    }

    @Override
    public void activateSpellEffect(MyHero myHero) {
        myHero.addMovement(movementspeed);
    }

}
