package desktop;

import character.hero.MyHero;
import controller.MainController;
import item.armor.ChestPlate;
import item.armor.Shield;
import item.potion.HealthPotion;
import item.weapon.Sword;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHeroTest extends MainController {
    private MyHero myHero;

    @Before
    public void setUp(){
        myHero = new MyHero(painter,batch);
    }

    /**
     * puts health potion to 10, heroes health also to 10 and checks if heroes health is 20 after using the health potion
     * */
    @Test
    public void myHeroHealthTest(){
        myHero.setHealth(10);
        new HealthPotion(painter,batch,"item/flask_big_red.png","Lebenstrank",10,0).useItem(myHero);
        assertEquals(myHero.getHealth(),20);
    }

    /**
     * puts health potion to 10, heroes health to 65 and checks if heroes health is 70 after using the health potion,
     * because the maximum health hero can have is 70
     * */
    @Test
    public void myHeroHealthAlmostFullTest(){
        myHero.setHealth(65);
        new HealthPotion(painter,batch,"item/flask_big_red.png","Lebenstrank",10,0).useItem(myHero);
        assertEquals(myHero.getHealth(),70);
    }

    /**
     * puts health potion to 20, heroes health to 50 and checks if heroes health is 70 after using the health potion
     * */
    @Test
    public void myHeroHealthFullTest(){
        myHero.setHealth(50);
        new HealthPotion(painter,batch,"item/flask_big_red.png","Lebenstrank",20,0).useItem(myHero);
        assertEquals(myHero.getHealth(),70);
    }

    /**
     * creates a weapon with 4 damage, uses it and checks if it added 4 to heroes strength which is 4
     * */
    @Test
    public void weaponTest(){
        new Sword(painter,batch,"item/weapon_knight_sword.png", "Schwert",4).useItem(myHero);
        assertEquals(myHero.getStrength(),8);
    }

    /**
     * sets heroes defense to 0, creates an armor with 5 defense and uses it, afterwards checks if hero has 5 defense now
     * */
    @Test
    public void armorTest(){
        myHero.setDefense(0);
        new Shield(painter,batch,"item/shieldBlack.png","schwarzes Schild",5).useItem(myHero);
        new ChestPlate(painter, batch, "item/chestPlate.png", "normale Ruestung",5).useItem(myHero);
        assertEquals(myHero.getDefense(),10);
    }

    /**
     * sets heroes defense to 0, creates two armor pieces with 5 defense and uses them, afterwards checks if hero has 5 defense now
     * */
    @Test
    public void twoArmorTest(){
        myHero.setDefense(0);
        new Shield(painter,batch,"item/shieldBlack.png","schwarzes Schild",5).useItem(myHero);
        new ChestPlate(painter, batch, "item/chestPlate.png", "normale Ruestung",5).useItem(myHero);
        assertEquals(myHero.getDefense(),10);
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
