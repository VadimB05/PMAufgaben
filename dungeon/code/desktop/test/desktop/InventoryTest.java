package desktop;

import controller.MainController;
import inventory.Inventory;
import item.potion.HealthPotion;
import item.potion.ManaPotion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest extends MainController {
    Inventory inventory1;

    MyHero myHero = new MyHero(painter,batch);
    int myMana=0;
    int myHealth=0;

    ManaPotion manaPotion = new ManaPotion(painter, batch, "item/flask_big_blue.png", "Manatrank");
    HealthPotion healthPotion = new HealthPotion(painter,batch,"item/flask_big_red.png", "Lebenstrank");

    @Before
    public void setInventory(){
        inventory1 = new Inventory();
        myMana = myHero.getMana();
        myHealth = myHero.getHealth();
        inventory1.addToInventory(manaPotion);
        inventory1.addToInventory(healthPotion);
    }

    /** Lässt sich Inventar anlegen und funktioniert die Methode <code>Inventory#addToInventory()</code>?*/
    @Test
    public void testAddToInventory() {
        inventory1.addToInventory(manaPotion);
    }

    /** Laesst sich ein Manatrank aus dem Inventar benutzen?*/
    @Test
    public void testManaPotion(){
        myMana += inventory1.getInventoryArrayList().get(0).setMana(myHero);
        myHealth += inventory1.getInventoryArrayList().get(0).setHeal(myHero);
        assertEquals(myMana,15);
        assertEquals(myHealth,30);
    }

    /** Laesst sich ein Lebenstrank aus dem Inventar benutzen?*/
    @Test
    public void testHealthPotion(){
        myMana += inventory1.getInventoryArrayList().get(1).setMana(myHero);
        myHealth += inventory1.getInventoryArrayList().get(1).setHeal(myHero);
        assertEquals(myMana,10);
        assertEquals(myHealth,40);
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
