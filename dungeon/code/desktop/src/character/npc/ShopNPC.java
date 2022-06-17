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
import inventory.Equipment;
import item.Items;
import item.armor.ChestPlate;
import item.armor.Shield;
import item.potion.HealthPotion;
import item.potion.Potion;
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
    private Texture currencyTexture;
    EntityController entityController;
    private List<Items> itemsList = new ArrayList<>();
    private ArrayList<Potion> inventoryItemsArrayList = new ArrayList<>();
    private MyHero hero;
    private Equipment equipment;
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

        currencyTexture = new Texture("item/currency.png");

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
        healthPotionAmount = 2;
    }

    @Override
    public void updateNotPaused() {

    }

    /**
     * checks if the shop npc and our hero are on the same spot
     *
     * @param hero, entitController, itemsList
     * */
    public Items checkNearShop(MyHero hero,
                               EntityController entityController,
                               ArrayList<Potion> inventoryItemsArrayList,
                               List<Items> itemsList){
        this.hero = hero;
        this.entityController = entityController;
        this.inventoryItemsArrayList = inventoryItemsArrayList;
        if(doesCollide(hero)){
            drawShop();
            tryToBuyItem(hero,itemsList);
        }
        return null;
    }

    public void checkNearShopSell(Equipment equipment){
        this.equipment = equipment;
        if(doesCollide(hero)){
            tryToSellItem();
        }
    }

    public void tryToSellItem(){
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(equipment.weaponEquipped()){
                if(collides(equipment.getWeapon().getHudEquipment())){
                    System.out.println("true");
                }
            }
            if(equipment.shieldEquipped()){
                if(collides(equipment.getShield().getHudEquipment())){
                    System.out.println("true");
                }
            }
            if(equipment.chestPlateEquipped()){
                if(collides(equipment.getChestPlate().getHudEquipment())){
                    System.out.println("true");
                }
            }

        }
    }

    /**
     * Checks if an item is buyable and then buys it
     * */
    private void tryToBuyItem(MyHero hero, List<Items> itemsList) {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(collides(shopItemSword) && hero.getBones()>=sword.getCost()){
                dropItem(sword);
                hero.substractBones(sword.getCost());
                sword.setBought(true);
                itemsList.add(sword);
            } else if(collides(shopItemShield) && hero.getBones()>= shield.getCost()){
                dropItem(shield);
                hero.substractBones(shield.getCost());
                shield.setBought(true);
                itemsList.add(shield);
            } else if(collides(shopItemChestplate) && hero.getBones()>= chestPlate.getCost()){
                dropItem(chestPlate);
                hero.substractBones(chestPlate.getCost());
                chestPlate.setBought(true);
                itemsList.add(chestPlate);
            } else if(collides(shopItemHealthPotion) && hero.getBones()>=healthPotion.getCost()){
                if(healthPotionAmount>0){
                    dropItem(healthPotion);
                    inventoryItemsArrayList.add(healthPotion);
                    hero.substractBones(healthPotion.getCost());
                    healthPotionAmount--;
                }
            }
        }
    }

    /**
     * The shop drops the bought item on the shops position
     * */
    private void dropItem(Items item) {
        entityController.add(item);
        item.setPosition(getPosition());
        item.setPickedUp(false);
    }

    private boolean collides(Rectangle rectangle){
        float f = Gdx.graphics.getHeight()-Gdx.input.getY();
        return rectangle.overlaps(new Rectangle().set((float)Gdx.input.getX(),(float)Gdx.graphics.getHeight()-Gdx.input.getY(),1,1));
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
        font.setColor(0.5f, 1f, 0f, 1);
        if(!sword.isBought()) {
            myBatch.draw(sword.getTexture(),
                shopItemSword.x,
                shopItemSword.y,
                shopItemSword.width,
                shopItemSword.height);
            font.draw(myBatch, costAmount = String.valueOf(sword.getCost()), 400, 300);
            myBatch.draw(currencyTexture,415,287,15,15);
        }

        if(!shield.isBought()) {
            myBatch.draw(shield.getTexture(),
                shopItemShield.x,
                shopItemShield.y,
                shopItemShield.width,
                shopItemShield.height);
            font.draw(myBatch, costAmount = String.valueOf(shield.getCost()), 400, 400);
            myBatch.draw(currencyTexture,415,387,15,15);
        }

        if(!chestPlate.isBought()) {
            myBatch.draw(chestPlate.getTexture(),
                shopItemChestplate.x,
                shopItemChestplate.y,
                shopItemChestplate.width,
                shopItemChestplate.height);
            font.draw(myBatch, costAmount = String.valueOf(chestPlate.getCost()), 550, 300);
            myBatch.draw(currencyTexture,565,287,15,15);
        }

        if(healthPotionAmount>0) {
            font.draw(myBatch, amount = String.valueOf(healthPotionAmount) + "x", 480, 400);
            myBatch.draw(healthPotion.getTexture(),
                shopItemHealthPotion.x,
                shopItemHealthPotion.y,
                shopItemHealthPotion.width,
                shopItemHealthPotion.height);
            font.draw(myBatch, costAmount = String.valueOf(healthPotion.getCost()), 550, 400);
            myBatch.draw(currencyTexture,565,387,15,15);
        }

        myBatch.end();
    }
}
