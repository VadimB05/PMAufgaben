package desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import controller.MainController;
import hud.Icon;
import inventory.Equipment;
import inventory.Inventory;
import item.Items;
import item.armor.ChestPlate;
import item.armor.Shield;
import item.potion.HealthPotion;
import item.potion.ManaPotion;
import item.potion.Potion;
import item.weapon.Staff;
import item.weapon.Sword;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import logging.StandardFormatter;
import tools.Point;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGame extends MainController {
    private ArrayList<Potion> inventoryItemsArrayList = new ArrayList<>();
    private Label levelLabel;
    private Label defenseLabel, healthLabel, damageLabel, manaLabel;
    private MyHero hero;
    private Sword sword;
    private Staff staff;
    private Shield shieldBlack;
    private Shield shieldMetall;
    private ChestPlate chestPlate;
    private ChestPlate chestPlateBlack;
    private Icon swordIcon, staffIcon, shieldBlackIcon, shieldMetallIcon, chestPlateIcon, chestPlateBlackIcon, healthPotionIcon,manaPotionIcon;
    private HealthPotion healthPotion;
    private ManaPotion manaPotion;
    private int levelCounter = 0;
    private int counterHUDInventory=1;
    Inventory inventory = new Inventory();
    Equipment equipment = new Equipment();

    Logger logger = Logger.getLogger(MyGame.class.getName());
    ConsoleHandler handlerMain = new ConsoleHandler();



    @Override
    protected void setup() {
        handlerMain.setLevel(Level.INFO);
        handlerMain.setFormatter(new StandardFormatter("Main Logger"));
        logger.setLevel(Level.INFO);
        logger.addHandler(handlerMain);
        logger.setUseParentHandlers(false);

        levelAPI.setGenerator(new LevelLoader());
        hero = new MyHero(painter,batch);

        sword = new Sword(painter,batch,"item/weapon_knight_sword.png", "Schwert",4);
        swordIcon = new Icon(hudPainter,hudBatch,new Point(515f,405f),sword.getTexturePath());

        staff = new Staff(painter, batch, "item/weapon_green_magic_staff.png", "Zauberstab",2);
        staffIcon = new Icon(hudPainter,hudBatch,new Point(515f,405f),staff.getTexturePath());

        shieldBlack = new Shield(painter, batch, "item/shieldBlack.png", "schwarzes Schild",2);
        shieldBlackIcon = new Icon(hudPainter,hudBatch,new Point(565f,335f),shieldBlack.getTexturePath());
        shieldMetall = new Shield(painter, batch, "item/shieldMetall.png", "metall Schild",5);
        shieldMetallIcon = new Icon(hudPainter,hudBatch,new Point(565f,335f),shieldMetall.getTexturePath());

        chestPlate = new ChestPlate(painter, batch, "item/chestPlate.png", "normale Ruestung",5);
        chestPlateIcon = new Icon(hudPainter,hudBatch,new Point(565f,405f),chestPlate.getTexturePath());
        chestPlateBlack = new ChestPlate(painter, batch, "item/chestPlateBlack.png", "schwarze Ruestung",15);
        chestPlateBlackIcon = new Icon(hudPainter,hudBatch,new Point(565f,405f),chestPlateBlack.getTexturePath());

        healthPotion = new HealthPotion(painter, batch,"item/flask_big_red.png", "Lebenstrank");

        manaPotion = new ManaPotion(painter, batch, "item/flask_big_blue.png", "Manatrank");


        inventoryItemsArrayList.add(healthPotion);
        inventoryItemsArrayList.add(manaPotion);

        // load the first level
        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            System.out.println("Es konnte kein Level geladen werden, bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }
        camera.follow(hero);

        entityController.add(shieldBlack);
        entityController.add(shieldMetall);

        entityController.add(sword);
        entityController.add(staff);

        entityController.add(chestPlate);
        entityController.add(chestPlateBlack);

        entityController.add(healthPotion);
        entityController.add(manaPotion);

        entityController.add(hero);

        hudController.add(new Icon(hudPainter,hudBatch,new Point(10f,10f),"hud/ui_heart_full.png"));
        hudController.add(new Icon(hudPainter,hudBatch,new Point(170f,400f),"hud/inventar.png"));
        hudController.add(new Icon(hudPainter,hudBatch,new Point(490f,330f),"hud/equipment.png"));
    }

    @Override
    protected void beginFrame() {
        loadStats();

        useItem();

        dropItemFromInventory();
    }

    @Override
    protected void endFrame() {

        delStats();

        if(levelAPI.getCurrentLevel().isOnEndTile(hero)){
            try {
                levelAPI.loadLevel();
            } catch (NoSolutionException e) {
                e.printStackTrace();
            }
        }

        switchWeapons();

        switchShields();

        switchChestpaltes();

        canItemBePickedUp();

        getInventoryItems();


    }

    /**
     * Switch chestplates
     *
     * <p> It checks if we have a chestplate equipped, switches them, saves the old one as a temporary item
     * removes the newly equipped chesplate from the map, reloads our players defense points and calls the method
     * changeItem(Items items, Icon equipIcon, Icon removeIcon)
     *
     * */
    private void switchChestpaltes() {
        if(checkAbleToPickUp(chestPlate,hero)){
            Items items = equipment.equipChestPlate(chestPlate);
            entityController.remove(chestPlate);
            hero.setDefense(equipment.getDefense());
            changeItem(items,chestPlateIcon,chestPlateBlackIcon);
        } else if (checkAbleToPickUp(chestPlateBlack,hero)) {
            Items items = equipment.equipChestPlate(chestPlateBlack);
            entityController.remove(chestPlateBlack);
            hero.setDefense(equipment.getDefense());
            changeItem(items,chestPlateBlackIcon,chestPlateIcon);
        }
    }

    /**
     * Switch shields
     *
     * <p> It checks if we have a shield equipped, switches them, saves the old one as a temporary item
     * removes the newly equipped shield from the map, reloads our players defense points and calls the method
     * changeItem(Items items, Icon equipIcon, Icon removeIcon)
     *
     * */
    private void switchShields() {
        if(checkAbleToPickUp(shieldBlack,hero)){
            Items items = equipment.equipShield(shieldBlack);
            entityController.remove(shieldBlack);
            hero.setDefense(equipment.getDefense());
            changeItem(items,shieldBlackIcon,shieldMetallIcon);
        } else if (checkAbleToPickUp(shieldMetall,hero)) {
            Items items = equipment.equipShield(shieldMetall);
            entityController.remove(shieldMetall);
            hero.setDefense(equipment.getDefense());
            changeItem(items,shieldMetallIcon,shieldBlackIcon);
        }
    }

    /**
     * Switch weapons
     *
     * <p> It checks if we have a weapons equipped, switches them, saves the old one as a temporary item
     * removes the newly equipped weapons from the map, reloads our players attack points and calls the method
     * changeItem(Items items, Icon equipIcon, Icon removeIcon)
     *
     * */
    private void switchWeapons() {
        if(checkAbleToPickUp(sword,hero)){
            Items items = equipment.equipWeapon(sword);
            entityController.remove(sword);
            hero.setStrength(sword.getDamage());
            changeItem(items,swordIcon,staffIcon);
        } else if (checkAbleToPickUp(staff,hero)) {
            Items items = equipment.equipWeapon(staff);
            entityController.remove(staff);
            hero.setStrength(staff.getDamage());
            changeItem(items,staffIcon,swordIcon);
        }
    }

    /** Check if inventory is empty and secondary items can be picked up
     *
     * <p> iterates through potions, checks if the poition can be picked up
     * if so, the potion gets picked up, removed from the map, pickedup gets set to true,
     * picked up potion gets added to the HUD inventory
     *
     * */
    private void canItemBePickedUp() {
        for(Potion potion : inventoryItemsArrayList){
            if (checkAbleToPickUp(potion,hero)){
                if(inventory.addToInventory(potion)){
                    entityController.remove(potion);
                    potion.setPickedUp(true);
                    addInventoryHUD(potion);
                    counterHUDInventory++;
                }
            }
        }
    }

    /** Add item texture to the end of the inventory HUD Arraylist
     *
     * <p> iterate through the inventory ArrayList, see how many items there are
     * and add the picked up item to the end of the ArrayList and HUD inventory
     *
     * */
    private void addInventoryHUD(Potion potion){
        if(potion.getClass().equals(manaPotion.getClass())){
            if(inventory.getInventoryArrayList().size()==1){
                counterHUDInventory=1;
                addMPToHUD();
            }else if(inventory.getInventoryArrayList().size()==2){
                counterHUDInventory=2;
                addMPToHUD();
            }else if(inventory.getInventoryArrayList().size()==3){
                counterHUDInventory=3;
                addMPToHUD();
            }else if(inventory.getInventoryArrayList().size()==4){
                counterHUDInventory=4;
                addMPToHUD();
            }
        }else {
            if(inventory.getInventoryArrayList().size()==1){
                counterHUDInventory=1;
                addHPToHUD();
            }else if(inventory.getInventoryArrayList().size()==2){
                counterHUDInventory=2;
                addHPToHUD();
            }else if(inventory.getInventoryArrayList().size()==3){
                counterHUDInventory=3;
                addHPToHUD();
            }else if(inventory.getInventoryArrayList().size()==4){
                counterHUDInventory=4;
                addHPToHUD();
            }
        }
        //hudController.add();
    }

    /** Add the health potion texture to the HUD */
    private void addHPToHUD(){
        healthPotionIcon = new Icon(hudPainter,hudBatch,new Point(121+(counterHUDInventory*69),415f),healthPotion.getTexturePath());
        hudController.add(healthPotionIcon);
    }

    /** Add the mana potion texture to the HUD */
    private void addMPToHUD(){
        manaPotionIcon = new Icon(hudPainter,hudBatch,new Point(121+(counterHUDInventory*69),415f),manaPotion.getTexturePath());
        hudController.add(manaPotionIcon);
    }

    /** Log all items in inventory*/
    private void getInventoryItems() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.I)){
            inventory.getInventoryItems();
        }
    }

    /** Dropped item set to picked up false, dropped at heroes position and removed from HUD
     *
     * Method to not pick up weapons, shields and chestplates into inventory
     *
     * */
//    private void changeItem(Items items, Icon equipIcon, Icon removeIcon){
//        hudController.add(equipIcon);
//        if(items!=null){
//            items.setLevel(levelAPI.getCurrentLevel());
//            entityController.add(items);
//            items.setPickedUp(false);
//            items.setPosition(hero.getPosition());
//            hudController.remove(removeIcon);
//        }//delete changeItem()
//    }
    /** Dropped item set to picked up false, dropped at heroes position and removed from HUD */
    private void changeItem(Items items, Icon equipIcon, Icon removeIcon){
        hudController.add(equipIcon);
        if(items!=null){
            inventory.addToInventory(items);
        }
    }

    /** Check if hero is in range with item, if the pick item key has been pressed and item isn`t already picked up*/
    private boolean checkAbleToPickUp(Items items, MyHero myHero){
        if(myHero.getPosition().x-items.getPosition().x < 1 && myHero.getPosition().y-items.getPosition().y < 1
            && myHero.getPosition().x-items.getPosition().x > -1 && myHero.getPosition().y-items.getPosition().y > -1 && !items.isPickedUp()
            && Gdx.input.isKeyJustPressed(Input.Keys.E)){
            return true;
        }else{
            return false;
        }
    }

    /** Select item from inventory ArrayList to use with input */
    private void useItem(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1) && inventory.getInventoryArrayList().size()>0) {
            useItemFromInventory(0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2) && inventory.getInventoryArrayList().size()>1) {
            useItemFromInventory(1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3) && inventory.getInventoryArrayList().size()>2) {
            useItemFromInventory(2);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4) && inventory.getInventoryArrayList().size()>3) {
            useItemFromInventory(3);
        }
    }

    /** Select item from inventory ArrayList to drop with input*/
    private void dropItemFromInventory(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6) && inventory.getInventoryArrayList().size()>0) {
            droppedItemManaOrHealth(0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7) && inventory.getInventoryArrayList().size()>1) {
            droppedItemManaOrHealth(1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8) && inventory.getInventoryArrayList().size()>2) {
            droppedItemManaOrHealth(2);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8) && inventory.getInventoryArrayList().size()>3) {
            droppedItemManaOrHealth(3);
        }
    }

    /** Delete dropped item from HUD Inventory
     *
     * <p> check what class our selected item has, remove it from the HUD
     * and delete it from the inventory ArrayList with the method
     * dropItem(int position, Items portion)
     *
     * @param i, to select an item from inventory ArrayList
     * */
    private void droppedItemManaOrHealth(int i){
        if(inventory.getInventoryArrayList().get(i).getClass().equals(manaPotion.getClass())){
            hudController.remove(manaPotionIcon);
            dropItem(i,inventory.getInventoryArrayList().get(i));
        }else{
            hudController.remove(healthPotionIcon);
            dropItem(i,inventory.getInventoryArrayList().get(i));
        }
    }

    /** Drop the item on heros position, set pickedup on false, delete item from inventory
     *
     * <p> show dropped item on the ground, add it to entities, set pickedup to false,
     * set the position of the dropped item to our heroes position and delete the item
     * from the inventory ArrayList
     *
     * @param position , position in the ArrayList
     * @param potion , the item that gets dropped
     * */
    private void dropItem(int position, Items potion){
        potion.setLevel(levelAPI.getCurrentLevel());
        entityController.add(inventory.getInventoryArrayList().get(position));
        potion.setPickedUp(false);
        potion.setPosition(hero.getPosition());
        inventory.dropItemInventory(position);
    }

    /** Differentiate between items, use the item, delete the item from the inventory
     *
     * <p> differentiate between health potion, mana potion and weapon, log what item we just used,
     * change our stats by the items stats, remove the item from the HUD inventory and drop it from
     * our inventory ArrayList
     *
     * @param position, position in the inventory ArrayList
     * */
    private void useItemFromInventory( int position){
        if(inventory.getInventoryArrayList().get(position).getClass().equals(manaPotion.getClass())){
            if(hero.getMaxMana()- hero.getMana()!=0) {
                logger.info(inventory.getInventoryArrayList().get(position).getName()+" verwendet.");
                hero.setMana(inventory.getInventoryArrayList().get(position).setMana(hero));
                hudController.remove(manaPotionIcon);
                inventory.dropItemInventory(position);
            }else{
                logger.info(inventory.getInventoryArrayList().get(position).getName()+" konnte nicht verwendet werden. Mana voll!");
            }
        }else if(inventory.getInventoryArrayList().get(position).getClass().equals(healthPotion.getClass())){
            if(hero.getMaxHealth()- hero.getHealth()!=0) {
                logger.info(inventory.getInventoryArrayList().get(position).getName()+" verwendet.");
                hero.setHealth(inventory.getInventoryArrayList().get(position).setHeal(hero));
                hudController.remove(healthPotionIcon);
                inventory.dropItemInventory(position);
            }else{
                logger.info(inventory.getInventoryArrayList().get(position).getName()+" konnte nicht verwendet werden. Leben voll!");
            }
        }else {
            if(inventory.getInventoryArrayList().get(position).getClass().equals(sword.getClass())) {
                logger.info(inventory.getInventoryArrayList().get(position).getName()+" ausgeruestet.");
                Items items = equipment.equipWeapon(sword);
                hero.setStrength(sword.getDamage());
                changeItem(items, swordIcon, staffIcon);
                inventory.dropItemInventory(position);
            }
            else{
                logger.info(inventory.getInventoryArrayList().get(position).getName()+" ausgeruestet.");
                Items items = equipment.equipWeapon(staff);
                entityController.remove(staff);
                hero.setStrength(staff.getDamage());
                changeItem(items, staffIcon, swordIcon);
                inventory.dropItemInventory(position);
            }
        }
    }


    /** Load the stats as labels in the HUD*/
    private void loadStats(){
        defenseLabel = hudController.drawText("Verteidigung: "+hero.getDefense(),"font/PublicPixel-0W5Kv.ttf", Color.YELLOW,10,50,50,20,100);
        damageLabel = hudController.drawText("Schaden: "+hero.getStrength(),"font/PublicPixel-0W5Kv.ttf", Color.YELLOW,10,50,50,20,120);
        healthLabel = hudController.drawText("Lebenspunkte: "+hero.getHealth(),"font/PublicPixel-0W5Kv.ttf", Color.YELLOW,10,50,50,20,140);
        manaLabel = hudController.drawText("Mana: "+hero.getMana(),"font/PublicPixel-0W5Kv.ttf", Color.YELLOW,10,50,50,20,80);
    }

    /** Delete labels for the stats to reload them*/
    private void delStats(){
        defenseLabel.remove();
        damageLabel.remove();
        healthLabel.remove();
        manaLabel.remove();
    }

    @Override
    public void onLevelLoad() {
        levelCounter++;
        if(levelCounter==1){
            levelLabel = hudController.drawText("Level "+levelCounter,"font/PublicPixel-0W5Kv.ttf", Color.YELLOW,14,50,50,20,5);
        }
        else{
            levelLabel.setText("Level "+levelCounter);
        }
        hero.setLevel(levelAPI.getCurrentLevel());

        sword.setLevel(levelAPI.getCurrentLevel());
        staff.setLevel(levelAPI.getCurrentLevel());

        shieldBlack.setLevel(levelAPI.getCurrentLevel());
        shieldMetall.setLevel(levelAPI.getCurrentLevel());

        chestPlate.setLevel(levelAPI.getCurrentLevel());
        chestPlateBlack.setLevel(levelAPI.getCurrentLevel());

        entityController.addAll(inventoryItemsArrayList);
        for(Items items : inventoryItemsArrayList){
            items.setPickedUp(false);
        }

        healthPotion.setLevel(levelAPI.getCurrentLevel());
        manaPotion.setLevel(levelAPI.getCurrentLevel());
    }

    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());

    }
}
