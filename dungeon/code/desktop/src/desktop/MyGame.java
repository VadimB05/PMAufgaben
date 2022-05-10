package desktop;

import character.Monster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.EntityController;
import controller.HUDController;
import controller.MainController;
import hud.GameOverWindow;
import hud.Icon;
import hud.Window;
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
import trap.Hole;
import trap.Spikes;
import character.monster.Chort;
import character.monster.Imp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyGame extends MainController {
    private ArrayList<Potion> inventoryItemsArrayList;
    private Label levelLabel;
    private Label defenseLabel, healthLabel, damageLabel, manaLabel;
    private MyHero hero;
    private Sword sword;
    private Staff staff;
    private Shield shieldBlack;
    private Shield shieldMetall;
    private ChestPlate chestPlate;
    private ChestPlate chestPlateBlack;
    private Icon swordIcon, staffIcon, shieldBlackIcon, shieldMetallIcon, chestPlateIcon, chestPlateBlackIcon, fullHeart, halfHeart, emptyHeart;
    private HealthPotion healthPotion;
    private ManaPotion manaPotion;
    private Spikes spikes;
    private Hole hole;
    private List<Monster> monsterList = new ArrayList<Monster>();
    Random monsterCountGenerator = new Random();;
    private int levelMonsterCount;
    private int maxMonsterCount = 5;
    private int levelCounter = 0;

    private Texture gameOverTexture;

    boolean paused;

    private SpriteBatch myBatch;

    Window window;

    Inventory inventory;
    Equipment equipment;

    Logger logger;
    ConsoleHandler handlerMain;





    @Override
    protected void setup() {
        myBatch = new SpriteBatch();

        window = new GameOverWindow();
        gameOverTexture = new Texture("hud/gameOver.png");

        equipment = new Equipment();

        logger = Logger.getLogger(this.getClass().getName());

        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }

        logger.setUseParentHandlers(false);
        handlerMain = new ConsoleHandler();

        handlerMain.setLevel(Level.INFO);
        handlerMain.setFormatter(new StandardFormatter("Main Logger"));
        logger.setLevel(Level.INFO);
        logger.addHandler(handlerMain);
        logger.setUseParentHandlers(false);

        inventoryItemsArrayList = new ArrayList<>();
        inventory = new Inventory();

        levelAPI.setGenerator(new LevelLoader());
        hero = new MyHero(painter,batch);
        spikes = new Spikes(painter,batch);
        hole = new Hole(painter,batch);


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

        healthPotion = new HealthPotion(painter, batch,"item/flask_big_red.png", "Lebenstrank",10,0);

        manaPotion = new ManaPotion(painter, batch, "item/flask_big_blue.png", "Manatrank",0,5);

        fullHeart = new Icon(hudPainter,hudBatch,new Point(10f,10f),"hud/ui_heart_full.png");
        halfHeart = new Icon(hudPainter,hudBatch,new Point(10f,10f),"hud/ui_heart_half.png");
        emptyHeart = new Icon(hudPainter,hudBatch,new Point(10f,10f),"hud/ui_heart_empty.png");

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

        entityController.add(spikes);
        entityController.add(hole);

        entityController.add(shieldBlack);
        entityController.add(shieldMetall);

        entityController.add(sword);
        entityController.add(staff);

        entityController.add(chestPlate);
        entityController.add(chestPlateBlack);

        entityController.add(healthPotion);
        entityController.add(manaPotion);

        entityController.add(hero);

        hudController.add(new Icon(hudPainter,hudBatch,new Point(490f,330f),"hud/equipment.png"));

        paused = false;
    }

    @Override
    protected void beginFrame() {
        isPausedRestart();

        loadStats();

        useItem();

        dropItemFromInventory();

        switchHUDHeart();

        removeHealth();

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

        if(spikes.collide(hero)){
            hero.addHealth(spikes.getDamage());
        }
        if(hole.collide(hero)){
            hero.addHealth(hole.getDamage());
        }

        switchWeapons();

        switchShields();

        switchChestPlates();

        canItemBePickedUp();

        getInventoryItems();

        gameOver();
    }

    /**
     * checks if the game is paused and if the player is trying to restart the game
     * */
    private void isPausedRestart(){
        if(paused && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            this.entityController = new EntityController();
            this.hudController = new HUDController(batch);
            this.setup();
            this.onLevelLoad();
        }
    }

    /**
     * checks if our Hero is dead, and if so, open a Game Over screen and let the player restart
     * */
    private void gameOver(){
        if(hero.getHealth()<= 0){
            myBatch.begin();
            myBatch.draw(window.getBackground(),0,0,1000,1000);
            myBatch.draw(window.getWindow(),50,50,540,380);
            myBatch.draw(gameOverTexture,50,50,540,380);
            myBatch.end();
            paused = true;
            hero.setPaused(true);
        }
    }

    //TODO: delete method, just for testing
    private void removeHealth(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            hero.setHealth(hero.getHealth()/2);
        }
    }

    /** Switches the Heart Icons in the HUD depending on the heroes health*/
    private void switchHUDHeart(){
        if((float) hero.getHealth()/hero.getMaxHealth()>=0.5f){
            hudController.remove(halfHeart);
            hudController.remove(emptyHeart);
            hudController.add(fullHeart);
        }else if(hero.getHealth()<= 0){
            hudController.remove(fullHeart);
            hudController.remove(halfHeart);
            hudController.add(emptyHeart);
        } else{
            hudController.remove(fullHeart);
            hudController.remove(emptyHeart);
            hudController.add(halfHeart);
        }
    }


    /**
     * Switch chestplates
     *
     * <p> It checks if we have a chestplate equipped, switches them, saves the old one as a temporary item
     * removes the newly equipped chesplate from the map, reloads our players defense points and calls the method
     * changeItem(Items items, Icon equipIcon, Icon removeIcon)
     *
     * */
    private void switchChestPlates() {
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
                }
            }
        }
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
    private void changeItem(Items items, Icon equipIcon, Icon removeIcon){
        hudController.add(equipIcon);
        if(items!=null){
            items.setLevel(levelAPI.getCurrentLevel());
            entityController.add(items);
            items.setPickedUp(false);
            items.setPosition(hero.getPosition());
            hudController.remove(removeIcon);
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9) && inventory.getInventoryArrayList().size()>3) {
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
            ManaPotion newManaPotion = new ManaPotion(painter, batch, "item/flask_big_blue.png", "Manatrank",0,5);
            dropItem(i,newManaPotion);
        }else{
            HealthPotion newHealthPotion = new HealthPotion(painter, batch,"item/flask_big_red.png", "Lebenstrank",10,0);
            dropItem(i,newHealthPotion);

        }
    }

    /** Drop the item on heros position, set pickedup on false, delete item from inventory
     *
     * <p> show dropped item on the ground, add it to entities, set pickedup to false,
     * set the position of the dropped item to our heroes position and delete the item
     * from the inventory ArrayList
     *
     * @param position , position in the ArrayList
     * */
    private void dropItem(int position,Potion newPotion){
        newPotion.setLevel(levelAPI.getCurrentLevel());
        entityController.add(newPotion);
        newPotion.setPickedUp(false);
        newPotion.setPosition(hero.getPosition());
        inventoryItemsArrayList.add(newPotion);
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
                inventory.getInventoryArrayList().get(position).useItem(hero);
                inventory.dropItemInventory(position);
            }else{
                logger.info(inventory.getInventoryArrayList().get(position).getName()+" konnte nicht verwendet werden. Mana voll!");
            }
        }else if(inventory.getInventoryArrayList().get(position).getClass().equals(healthPotion.getClass())){
            if(hero.getMaxHealth()- hero.getHealth()!=0) {
                logger.info(inventory.getInventoryArrayList().get(position).getName()+" verwendet.");
                inventory.getInventoryArrayList().get(position).useItem(hero);
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
        healthLabel = hudController.drawText("Lebenspunkte: "+(int)((float) hero.getHealth()/hero.getMaxHealth()*100)+"%","font/PublicPixel-0W5Kv.ttf", Color.YELLOW,10,50,50,20,140);
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

        spikes.setLevel(levelAPI.getCurrentLevel());
        hole.setLevel(levelAPI.getCurrentLevel());

        // random number of monsters gets generated based on current level (max 5)
        levelMonsterCount = monsterCountGenerator.nextInt(3) + levelCounter;
        if(levelMonsterCount > 5) { levelMonsterCount = 5; }
        for(int i = 0; i < levelMonsterCount; i++) {
            monsterList.add(new Chort(painter, batch));
            monsterList.add(new Imp(painter, batch));
        }

        // added to the entityController
        for(int i = 0; i < levelMonsterCount * 2; i++) {
            entityController.add(monsterList.get(i));
        }

        // and then loaded into the level
        for(int i = 0; i < levelMonsterCount * 2; i++) {
            monsterList.get(i).setLevel(levelAPI.getCurrentLevel());
        }
    }

    /**
     * The program entry point to start the dungeon.
     *
     * @param args command line arguments, but not needed.
     */
    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());

    }
}
