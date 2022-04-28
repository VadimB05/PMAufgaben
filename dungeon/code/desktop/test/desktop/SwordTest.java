package desktop;

import controller.MainController;
import item.weapon.Sword;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SwordTest extends MainController {
    private Sword s;

    @Before
    public void setS(){
        s = new Sword(painter,batch,"item/weapon_knight_sword.png", "Schwert");
    }

    /** Lässt sich ein Sword anlegen und funktioniert die Methode <code>Sword#getName()</code>?*/
    @Test
    public void testSword() {
        assertEquals(s.getName(),"Schwert");
    }


    @Override
    protected void setup() {

    }

    @Override
    protected void beginFrame() {

    }

    @Override
    protected void endFrame() {

    }

    @Override
    public void onLevelLoad() {

    }
}
