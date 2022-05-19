package magic;


import desktop.MyHero;

public class MovementSpell extends Spells {
    private float movementspeed = 0.4f;
    private String name = "Movementspell";

    public MovementSpell() {
    }

    @Override
    public String getName() {
        return "Geschwindigkeitsspruch";
    }

    @Override
    public void castSpell(MyHero myHero) {
        myHero.addMovement(movementspeed);
    }

}
