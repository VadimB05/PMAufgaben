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
import logging.InventoryFormatter;
import tools.Point;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import trap.Hole;
import trap.Spikes;
import character.monster.Chort;
import character.monster.Imp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyGame extends MainController {
    private ArrayList<Potion> inventoryItemsArrayList;
    private List<Items> itemsList = new ArrayList<>();
    private Label levelLabel;
    private Label defenseLabel, healthLabel, damageLabel, manaLabel;
    private MyHero hero;
    private Sword sword;
    private Staff staff;
    private Shield shieldBlack;
    private Shield shieldMetall;
    private ChestPlate chestPlate;
    private ChestPlate chestPlateBlack;
    private Icon fullHeart, halfHeart, emptyHeart;
    private HealthPotion healthPotion;
    private ManaPotion manaPotion;
    private Spikes spikes;
    private Hole hole;
    private List<Monster> monsterList;
    Random monsterCountGenerator = new Random();
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

        monsterList = new ArrayList<Monster>();

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
        handlerMain.setFormatter(new InventoryFormatter("Main Logger"));
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
        sword.setIcon(new Icon(hudPainter,hudBatch,new Point(515f,405f),sword.getTexturePath()));

        staff = new Staff(painter, batch, "item/weapon_green_magic_staff.png", "Zauberstab",2);
        staff.setIcon(new Icon(hudPainter,hudBatch,new Point(515f,405f),staff.getTexturePath()));

        shieldBlack = new Shield(painter, batch, "item/shieldBlack.png", "schwarzes Schild",2);
        shieldBlack.setIcon(new Icon(hudPainter,hudBatch,new Point(565f,335f),shieldBlack.getTexturePath()));
        shieldMetall = new Shield(painter, batch, "item/shieldMetall.png", "metall Schild",5);
        shieldMetall.setIcon(new Icon(hudPainter,hudBatch,new Point(565f,335f),shieldMetall.getTexturePath()));

        chestPlate = new ChestPlate(painter, batch, "item/chestPlate.png", "normale Ruestung",5);
        chestPlate.setIcon(new Icon(hudPainter,hudBatch,new Point(565f,405f),chestPlate.getTexturePath()));
        chestPlateBlack = new ChestPlate(painter, batch, "item/chestPlateBlack.png", "schwarze Ruestung",15);
        chestPlateBlack.setIcon(new Icon(hudPainter,hudBatch,new Point(565f,405f),chestPlateBlack.getTexturePath()));

        healthPotion = new HealthPotion(painter, batch,"item/flask_big_red.png", "Lebenstrank",10,0);

        manaPotion = new ManaPotion(painter, batch, "item/flask_big_blue.png", "Manatrank",0,5);

        fullHeart = new Icon(hudPainter,hudBatch,new Point(10f,10f),"hud/ui_heart_full.png");
        halfHeart = new Icon(hudPainter,hudBatch,new Point(10f,10f),"hud/ui_heart_half.png");
        emptyHeart = new Icon(hudPainter,hudBatch,new Point(10f,10f),"hud/ui_heart_empty.png");

        inventoryItemsArrayList.add(healthPotion);
        inventoryItemsArrayList.add(manaPotion);

        Collections.addAll(itemsList,sword, staff, shieldBlack, shieldMetall, chestPlateBlack, chestPlate);

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

        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            switchEquipment();
            canItemBePickedUp();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && hero.getFrameCounter()>=50) {
            checkMonsterAttackable();
        }

        monsterAttackPlayer();

        getInventoryItems();

        gameOver();

        countWaitBetweenAttacks();
    }

    private void monsterAttackPlayer() {
        for(Monster monster: monsterList){
            if(monster.collide(hero)){
                if(monster.getFrameCounter()>= 50){
                    hero.getAttacked(monster);
                    monster.resetFrameCounter();
                }
                monster.setInCombat(true);
            }else {
                monster.setInCombat(false);
            }
        }
    }

    /**
     * Iterates through the monsters and checks collision, if true call method attackMonster()
     * If the monster loses all health, then remove the object from the map and arraylist
     * */
    private void checkMonsterAttackable() {
        for(int i=0; i<monsterList.size();i++){
            if(monsterList.get(i).collide(hero)){
                attackMonster(monsterList.get(i));
                if(monsterList.get(i).checkMonsterAlive()){
                    logger.info("Monster wurde eliminiert!");
                    entityController.remove(monsterList.get(i));
                    monsterList.remove(i);
                    i--;
                }
            }
        }
    }

    private void countWaitBetweenAttacks() {
        hero.addFrameCounter();
        for(Monster monster : monsterList){
            monster.addFrameCounter();
        }
    }

    private void attackMonster(Monster monster) {
        hero.attack(monster);
        hero.resetFrameCounter();
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
        }else{
            hudController.remove(fullHeart);
            hudController.remove(emptyHeart);
            hudController.add(halfHeart);
        }
    }

    /**
     * Switch equipment
     *
     * <p> It checks if we have an equipment of that type is already equipped, switches them, saves the old one as a temporary item
     * removes the newly equipped item from the map, reloads our players defense/strength points and calls the method
     * changeItem(Items items)
     *
     * */
    private void switchEquipment() {
        for(Items items: itemsList){
            if(items.collide(hero) && !items.isPickedUp()){
                Items dropItem = equipment.equipmentChange(items);
                entityController.remove(items);
                items.useItem(hero);
                hero.setDefense(equipment.getDefense());
                hudController.add(items.getIcon());
                changeItem(dropItem);
                return;
            }
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
            if (potion.collide(hero) && !potion.isPickedUp()){
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
    private void changeItem(Items items){
        if(items!=null) {
            items.setLevel(levelAPI.getCurrentLevel());
            entityController.add(items);
            items.setPickedUp(false);
            items.setPosition(hero.getPosition());
            hudController.remove(items.getIcon());
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
            monsterList.add(new Chort(painter, batch,(2*levelCounter)+10,levelCounter));
            monsterList.add(new Imp(painter, batch,(2*levelCounter)+10,levelCounter));
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
