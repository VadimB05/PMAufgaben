package desktop;

import controller.MainController;
import inventory.Inventory;
import item.Items;
import item.weapon.Sword;
import org.junit.Before;
import org.junit.Test;


public class InventoryTest<Items> extends MainController {
    Inventory inventory1;

    Sword sword = new Sword(painter,batch,"item/weapon_knight_sword.png","Schwert");

    @Before
    public void setInventory(){
        inventory1 = new Inventory();
    }

    /** Lässt sich Inventar anlegen und funktioniert die Methode <code>Inventory#addToInventory()</code>?*/
    @Test
    public void testAddToInventory() {
        inventory1.addToInventory(sword);
    }

    /** Funktioniert die Methode <code>Inventory#getInventoryItems()</code>?*/
    @Test
    public void testGetInventoryItems(){
        inventory1.getInventoryItems();
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
