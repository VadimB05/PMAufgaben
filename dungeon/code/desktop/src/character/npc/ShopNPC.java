package character.npc;

import character.Character;
import character.hero.MyHero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.EntityController;
import graphic.Animation;
import graphic.Painter;
import hud.ShopWindow;
import hud.Window;
import item.Items;
import item.armor.ChestPlate;
import item.armor.Shield;
import item.potion.HealthPotion;
import item.weapon.Sword;
import level.elements.Level;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class ShopNPC extends Character {
    private SpriteBatch myBatch;
    private Sword sword;
    private ChestPlate chestPlate;
    private Shield shield;
    private HealthPotion healthPotion;
    private int healthPotionAmount;
    private String amount;
    private String costAmount;
    private Rectangle shopItemSword;
    private Rectangle shopItemShield;
    private Rectangle shopItemChestplate;
    private Rectangle shopItemHealthPotion;
    EntityController entityController;
    private List<Items> itemsList = new ArrayList<>();
    Window shopWindow;
    /**
     * Must be implemented for all objects that should be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */
    public ShopNPC(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        idleAnimationRightList.add("character/npc/shop_idle.png");
        animation = new Animation(idleAnimationRightList,8);

        sword = new Sword(painter,batch,
            "item/weapon_knight_swordBronze.png",
            "Bronze Schwert",
            6);
        shopItemSword = new Rectangle();
        shopItemSword.set( 350, 280, 40, 40);
        sword.setCost(5);

        shield = new Shield(painter, batch,
            "item/shieldBronze.png",
            "Bronze Schild",
            4);
        shopItemShield = new Rectangle();
        shopItemShield.set(350, 380, 40, 40);
        shield.setCost(5);

        chestPlate = new ChestPlate(painter, batch,
            "item/chestPlateBronze.png",
            "Bronze Ruestung",
            8);
        shopItemChestplate = new Rectangle();
        shopItemChestplate.set(500, 280, 40, 40);
        chestPlate.setCost(5);

        healthPotion = new HealthPotion(painter, batch,
            "item/flask_big_red.png",
            "Lebenstrank",
            10,
            0);
        shopItemHealthPotion = new Rectangle();
        shopItemHealthPotion.set(500, 380, 40, 40);
        healthPotion.setCost(2);

        myBatch = new SpriteBatch();
        shopWindow = new ShopWindow();
        healthPotionAmount = 5;
    }

    @Override
    public void updateNotPaused() {

    }

    public Items checkNearShop(MyHero hero, EntityController entityController, List<Items> itemsList){
        this.entityController = entityController;
        if(doesCollide(hero)){
            drawShop();
            checkBought(hero,itemsList);
        }
        return null;
    }

    private void checkBought(MyHero hero, List<Items> itemsList) {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(collides(shopItemSword) && hero.getBones()>=sword.getCost()){
                dropItem(sword,hero);
                hero.substractBones(sword.getCost());
                sword.setBought(true);
                itemsList.add(sword);
            } else if(collides(shopItemShield) && hero.getBones()>= shield.getCost()){
                dropItem(shield,hero);
                hero.substractBones(shield.getCost());
                shield.setBought(true);
                itemsList.add(shield);
            } else if(collides(shopItemChestplate) && hero.getBones()>= chestPlate.getCost()){
                dropItem(chestPlate,hero);
                hero.substractBones(chestPlate.getCost());
                chestPlate.setBought(true);
                itemsList.add(chestPlate);
            } else if(collides(shopItemHealthPotion) && hero.getBones()>=healthPotion.getCost()){
                dropItem(healthPotion,hero);
                hero.substractBones(healthPotion.getCost());
                healthPotion.setBought(true);
            }
        }
    }

    private void dropItem(Items item, MyHero hero) {
        entityController.add(item);
        item.setPosition(hero.getPosition());
        item.setPickedUp(false);
    }

    private boolean collides(Rectangle rectangle){
        return rectangle.overlaps(new Rectangle().set((float)Gdx.input.getX(),(float)480-Gdx.input.getY(),0,0));
    }

    /**
     * Checks if our hero is colliding with questNPC
     * */
    private boolean doesCollide(MyHero hero){
        return hero.getHitBox().overlaps(getHitBox());
    }

    /**
     * Sets character on random position
     *
     * @param level     Current level
     */
    public void setLevel(Level level){
        currentLevel = level;
        position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
    }

    private void drawShop() {
        myBatch.begin();
        BitmapFont font = new BitmapFont();
        font.setColor(0f, 0f, 0f, 1);
        myBatch.draw(shopWindow.getWindow(), 350, 250, 280, 200);
        font.draw(myBatch, "Shop", 355, 445);

        if(!sword.isBought()) {
            myBatch.draw(sword.getTexture(),
                shopItemSword.x,
                shopItemSword.y,
                shopItemSword.width,
                shopItemSword.height);
            font.draw(myBatch, costAmount = String.valueOf(sword.getCost()), 400, 300);
        }

        if(!shield.isBought()) {
            myBatch.draw(shield.getTexture(),
                shopItemShield.x,
                shopItemShield.y,
                shopItemShield.width,
                shopItemShield.height);
            font.draw(myBatch, costAmount = String.valueOf(shield.getCost()), 400, 400);
        }

        if(!chestPlate.isBought()) {
            myBatch.draw(chestPlate.getTexture(),
                shopItemChestplate.x,
                shopItemChestplate.y,
                shopItemChestplate.width,
                shopItemChestplate.height);
            font.draw(myBatch, costAmount = String.valueOf(chestPlate.getCost()), 550, 300);
        }

        if(!healthPotion.isBought()) {
            font.draw(myBatch, amount = String.valueOf(healthPotionAmount) + "x", 480, 400);
            myBatch.draw(healthPotion.getTexture(),
                shopItemHealthPotion.x,
                shopItemHealthPotion.y,
                shopItemHealthPotion.width,
                shopItemHealthPotion.height);
            font.draw(myBatch, costAmount = String.valueOf(healthPotion.getCost()), 550, 400);
        }

        myBatch.end();
    }
}
