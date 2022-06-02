package desktop;


import ability.*;


import character.hero.MyHero;
import character.monster.Monster;
import character.monster.Variant;

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
import quest.Quest;
import quest.QuestLog;
import quest.QuestType;
import magic.Spellbook;
import tools.Point;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import trap.Hole;
import trap.Spikes;
import magic.*;
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
    private QuestLog questLog;
    private Label defenseLabel;
    private Label healthLabel;
    private Label damageLabel;
    private Label manaLabel;
    private Label levelLabel;
    private Label expLabel;
    private Label stageLabel;
    private MyHero hero;
    private Sword sword;
    private Staff staff;
    private Shield shieldBlack;
    private Shield shieldMetall;
    private ChestPlate chestPlate;
    private ChestPlate chestPlateBlack;
    private Icon fullHeart;
    private Icon halfHeart;
    private Icon emptyHeart;
    private HealthPotion healthPotion;
    private ManaPotion manaPotion;
    private Spikes spikes;
    private Hole hole;
    private List<Monster> monsterList;
    Random monsterCountGenerator = new Random();
    private int levelMonsterCount;
    private int maxMonsterCount;
    public static int stageCounter;
    private Texture gameOverTexture;
    boolean paused;
    private SpriteBatch myBatch;
    private Quest findScroll;
    private Quest killMonster;
    private Quest reachLevel;
    Spellbook spellbook;
    private MovementSpell movementSpell;
    private LifeSpell lifespell;
    AbilityTree abilityTree;
    private Blackhole blackhole;
    private Healability healability;
    private PowerUpability powerUpability;
    Window window;
    Inventory inventory;
    Equipment equipment;
    Logger logger;
    ConsoleHandler handlerMain;

    @Override
    protected void setup() {
        myBatch = new SpriteBatch();
        monsterList = new ArrayList<>();
        window = new GameOverWindow();
        gameOverTexture = new Texture("hud/gameOver.png");
        equipment = new Equipment();
        logger = Logger.getLogger(this.getClass().getName());
        questLog = new QuestLog();

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

        abilityTree = new AbilityTree();
        blackhole = new Blackhole();
        healability = new Healability();
        powerUpability = new PowerUpability();

        spellbook = new Spellbook();
        lifespell = new LifeSpell();
        movementSpell = new MovementSpell();

        levelAPI.setGenerator(new LevelLoader());
        hero = new MyHero(painter,batch);
        spikes = new Spikes(painter,batch);
        hole = new Hole(painter,batch);


        sword = new Sword(painter,batch,
                "item/weapon_knight_sword.png",
                "Schwert",
                4);
        staff = new Staff(painter, batch,
                "item/weapon_green_magic_staff.png",
                "Zauberstab",
                2);
        shieldBlack = new Shield(painter, batch,
                "item/shieldBlack.png",
                "schwarzes Schild",
                2);
        shieldMetall = new Shield(painter, batch,
                "item/shieldMetall.png",
                "metall Schild",
                5);
        chestPlate = new ChestPlate(painter, batch,
                "item/chestPlate.png",
                "normale Ruestung",
                5);
        chestPlateBlack = new ChestPlate(painter, batch,
                "item/chestPlateBlack.png",
                "schwarze Ruestung",
                15);
        healthPotion = new HealthPotion(painter, batch,
                "item/flask_big_red.png",
                "Lebenstrank",
                10,
                0);
        manaPotion = new ManaPotion(painter, batch,
                "item/flask_big_blue.png",
                "Manatrank",
                0,
                5);
        fullHeart = new Icon(hudPainter,hudBatch,
                new Point(10f,10f),
            "hud/ui_heart_full.png");
        halfHeart = new Icon(hudPainter,hudBatch,
                new Point(10f,10f),
            "hud/ui_heart_half.png");
        emptyHeart = new Icon(hudPainter,hudBatch,
                new Point(10f,10f),
            "hud/ui_heart_empty.png");

        maxMonsterCount = 5;
        stageCounter = 0;
        inventoryItemsArrayList.add(healthPotion);
        inventoryItemsArrayList.add(manaPotion);

        Collections.addAll(itemsList,
                sword,
                staff,
                shieldBlack,
                shieldMetall,
                chestPlateBlack,
                chestPlate);

        // load the first level
        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            System.out.println("Es konnte kein Level geladen werden,"
                    + " bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }
        camera.follow(hero);

        entityController.add(spikes);
        entityController.add(hole);

        entityController.add(shieldBlack);
        entityController.add(sword);
        entityController.add(staff);
        entityController.add(chestPlate);
        entityController.add(healthPotion);
        entityController.add(manaPotion);
        entityController.add(hero);

        hudController.add(new Icon(hudPainter,hudBatch,new Point(490f,330f),"hud/equipment.png"));

        paused = false;

        createQuests();
    }

    @Override
    protected void beginFrame() {
        isPausedRestart();

        //comment out to make the game smooth
        loadStats();
        useItem();
        dropItemFromInventory();
        switchHUDHeart();

        removeHealth();

        useSpell();

        useAbility();

    }

    @Override
    protected void endFrame() {

        //comment out with loadStats()
        delStats();

        if(levelAPI.getCurrentLevel().isOnEndTile(hero)){
            try {
                levelAPI.loadLevel();
            } catch (NoSolutionException e) {
                e.printStackTrace();
            }
        }

        collideTrap();

        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            switchEquipment();
            canItemBePickedUp();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && hero.getFrameCounter()>=50) {
            checkMonsterAttackable();
        }

        monsterAttackPlayer();
        questLog.logQuest();
        getInventoryItems();
        gameOver();
        countWaitBetweenAttacks();

        if(Gdx.input.isKeyJustPressed(Input.Keys.O)){
            killMonster.setQuestAccepted(questLog,hero,entityController);
            findScroll.setQuestAccepted(questLog,hero,entityController);
            reachLevel.setQuestAccepted(questLog,hero,entityController);
            hero.setQuest(reachLevel);
            hero.setHaveQuest(true);
        }
    }

    private void collideTrap() {
        if(spikes.collide(hero)){
            hero.addHealth(spikes.getDamage());
        }
        if(hole.collide(hero)){
            hero.addHealth(hole.getDamage());
        }
    }

    private void monsterAttackPlayer() {
        for(Monster monster: monsterList){
            if(monster.collide(hero)){
                if(monster.getFrameCounter()>= 50){
                    monster.attack(hero);
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
                if(monsterList.get(i).checkDead()){
                    logger.info("Monster wurde eliminiert!");
                    entityController.remove(monsterList.get(i));
                    hero.gainExp(monsterList.get(i).getExp());
                    monsterList.remove(i);
                    i--;
                    if(killMonster.isQuestAccepted()){
                        killMonster.update();
                    }
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
        getInventoryItems();
        showAbilityTree();
        showSpellbook();
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
        if(hero.checkDead()){
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
        }else{
            hudController.remove(fullHeart);
            hudController.remove(emptyHeart);
            hudController.add(halfHeart);
        }
    }

    /**
     * Switch equipment
     *
     * <p> It checks if we have an equipment of that type is already equipped,
     * switches them, saves the old one as a temporary item
     * removes the newly equipped item from the map,
     * reloads our players defense/strength points and calls the method
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)
                && inventory.getInventoryArrayList().size()>0) {
            useItemFromInventory(0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)
                && inventory.getInventoryArrayList().size()>1) {
            useItemFromInventory(1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)
                && inventory.getInventoryArrayList().size()>2) {
            useItemFromInventory(2);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)
                && inventory.getInventoryArrayList().size()>3) {
            useItemFromInventory(3);
        }
    }

    /** Select item from inventory ArrayList to drop with input*/
    private void dropItemFromInventory(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)
                && inventory.getInventoryArrayList().size()>0) {
            droppedItemManaOrHealth(0);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)
                && inventory.getInventoryArrayList().size()>1) {
            droppedItemManaOrHealth(1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)
                && inventory.getInventoryArrayList().size()>2) {
            droppedItemManaOrHealth(2);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)
                && inventory.getInventoryArrayList().size()>3) {
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
            ManaPotion newManaPotion = new ManaPotion(painter, batch,
                    "item/flask_big_blue.png",
                    "Manatrank",
                    0,
                    5);
            dropItem(i,newManaPotion);
        }else{
            HealthPotion newHealthPotion = new HealthPotion(painter, batch,
                    "item/flask_big_red.png",
                    "Lebenstrank",
                    10,
                    0);
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
        if(inventory.getInventoryArrayList().get(position).getClass().equals(
                manaPotion.getClass())){
            if(hero.getMaxMana()- hero.getMana()!=0) {
                logger.info(inventory.getInventoryArrayList().get(position).getName()
                        +" verwendet.");
                inventory.getInventoryArrayList().get(position).useItem(hero);
                inventory.dropItemInventory(position);
            }else{
                logger.info(inventory.getInventoryArrayList().get(position).getName()
                        +" konnte nicht verwendet werden. Mana voll!");
            }
        }else if(inventory.getInventoryArrayList().get(position).getClass().equals(
                healthPotion.getClass())){
            if(hero.getMaxHealth()- hero.getHealth()!=0) {
                logger.info(inventory.getInventoryArrayList().get(position).getName()
                        +" verwendet.");
                inventory.getInventoryArrayList().get(position).useItem(hero);
                inventory.dropItemInventory(position);
            }else{
                logger.info(inventory.getInventoryArrayList().get(position).getName()
                        +" konnte nicht verwendet werden. Leben voll!");
            }
        }
    }

    /** Activates the effect of the spell given as a parameter,
     * if the hero has enough mana and is deep enough into the dungeon */
    private void castSpell(Spells spell) {
        if(stageCounter >= spell.getAvailableAtLevel()) {
            if(hero.getMana() >= spell.getManaCost()) {
                spell.activateSpellEffect(hero);
                hero.removeMana(spell.getManaCost());
                logger.info("Du hast den " + spell.getName() + " benutzt.");
            }
            else {
                logger.info("Du hast nicht genug Mana fuer den " + spell.getName() +
                    ". Du brauchst mindestens " + spell.getManaCost() + ".");
            }
        }
        else {
            logger.info("Du musst Ebene " + spell.getAvailableAtLevel() + " erreicht haben, um den "
                + spell.getName() + " zu benutzen.");
        }
    }

    /** Calls the castSpell() method on button press to use spells */
    private void useSpell() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            castSpell(lifespell);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            castSpell(movementSpell);
        }
    }

    /** Log all Spells in Spellbook*/
    private void showSpellbook() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.M)){
            spellbook.showSpellbook();
        }
    }
    private void castAbility(Abilitys ability){
        if(hero.getLevel() >= ability.getAvailableAtHeroLevel()){
            if(hero.getMana() >= ability.getManaCost()){
                ability.activateAbility(hero);
                hero.removeMana(ability.getManaCost());
                logger.info("Du hast den " + ability.getName() + " benutzt.");
            }
            else {
                logger.info("Du hast nicht genug Mana für den " + ability.getName() +
                    ". Du brauchst mindestens " + ability.getManaCost() + ".");
            }

        } else {
            logger.info("Du musst " + ability.getAvailableAtHeroLevel() + " erreicht haben, um den "
             + ability.getName() + " zu benutzen.");
        }
    }

    private void useAbility(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.H)){
            castAbility(healability);

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            castAbility(powerUpability);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
            castAbility(blackhole);
        }
    }

    private void showAbilityTree(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)){
            abilityTree.showAbilityTree();
        }

    }

    /** Load the stats as labels in the HUD*/
    private void loadStats(){
        defenseLabel = hudController.drawText(
            "Verteidigung: "+hero.getDefense(),
            "font/PublicPixel-0W5Kv.ttf", Color.YELLOW,
            10,50,50,20,100);
        damageLabel = hudController.drawText(
            "Schaden: "+hero.getStrength(),
            "font/PublicPixel-0W5Kv.ttf", Color.YELLOW,
            10,50,50,20,120);
        healthLabel = hudController.drawText(
            "Lebenspunkte: "+hero.getHealth()+"/"+hero.getMaxHealth(),
            "font/PublicPixel-0W5Kv.ttf", Color.YELLOW,
            10,50,50,20,140);
        manaLabel = hudController.drawText("Mana: "+hero.getMana()+"/"+hero.getMaxMana(),
            "font/PublicPixel-0W5Kv.ttf", Color.YELLOW,
            10,50,50,20,80);
        levelLabel = hudController.drawText("Level: "+hero.getLevel(),
            "font/PublicPixel-0W5Kv.ttf", Color.YELLOW,
            14,50,50,20,5);
        expLabel = hudController.drawText(
            "Erfahrungspunkte: "+(int)((float)hero.getExp()/hero.getReqExp()*100)+"%",
            "font/PublicPixel-0W5Kv.ttf", Color.YELLOW,
            10,50,50,20,30);
        stageLabel = hudController.drawText("Ebene "+ stageCounter,
            "font/PublicPixel-0W5Kv.ttf", Color.YELLOW,
            14,50,50,500,440);
    }

    /** Delete labels for the stats to reload them*/
    private void delStats(){
        defenseLabel.remove();
        damageLabel.remove();
        healthLabel.remove();
        manaLabel.remove();
        levelLabel.remove();
        expLabel.remove();
        stageLabel.remove();
    }

    @Override
    public void onLevelLoad() {
        stageCounter++;
        if(stageCounter>1){
            hero.gainExp(20);
        }

        hero.setLevel(levelAPI.getCurrentLevel());
        sword.setLevel(levelAPI.getCurrentLevel());
        staff.setLevel(levelAPI.getCurrentLevel());
        shieldBlack.setLevel(levelAPI.getCurrentLevel());
        chestPlate.setLevel(levelAPI.getCurrentLevel());

        for(Items items : inventoryItemsArrayList){
            entityController.add(items);
            items.setPickedUp(false);
        }

        healthPotion.setLevel(levelAPI.getCurrentLevel());
        manaPotion.setLevel(levelAPI.getCurrentLevel());
        spikes.setLevel(levelAPI.getCurrentLevel());
        hole.setLevel(levelAPI.getCurrentLevel());

        // random number of monsters gets generated based on current level (max 5)
        levelMonsterCount = monsterCountGenerator.nextInt(3) + stageCounter;
        if(levelMonsterCount > 5) { levelMonsterCount = 5; }
        for(int i = 0; i < levelMonsterCount; i++) {
            monsterList.add(new Monster(painter,
                    batch,
                    new Variant(Variant.Variants.CHORT)));
            monsterList.add(new Monster(painter,
                    batch,
                    new Variant(Variant.Variants.IMP)));
        }

        // added to the entityController and loaded in the level
        for(int i = 0; i < levelMonsterCount * 2; i++) {
            entityController.add(monsterList.get(i));
            monsterList.get(i).setLevel(levelAPI.getCurrentLevel());
	}
    }

    private void createQuests() {
        if(stageCounter==1){
            final QuestType killQuest = new QuestType("Erledige Monster",
                    hero,
                    QuestType.Quests.KILL);
            killMonster = killQuest.newQuest(null);
        }
        if(stageCounter==1){
            final QuestType findQuest = new QuestType("Die versteckte Schriftrolle",
                    hero,
                    QuestType.Quests.FIND);
            findScroll = findQuest.newQuest(shieldMetall);
        }
        if(stageCounter==1){
            final QuestType levelQuest = new QuestType("Level Quest",
                    hero,
                    QuestType.Quests.LEVEL);
            reachLevel = levelQuest.newQuest(chestPlateBlack);
        }
    }

    /** Main method. Starts the game */
    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
