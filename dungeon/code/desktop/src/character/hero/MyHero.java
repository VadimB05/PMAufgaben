package character.hero;

import character.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.elements.Level;
import observer.QuestObservable;
import quest.Quest;
import tools.Point;
import java.util.ArrayList;
import java.util.List;

public class MyHero extends Character implements QuestObservable {
    private int mana;
    private int defense;
    private int maxMana;
    private int maxHealth;
    private int baseStrength;
    private int reqExp;
    private int level;
    private int bones;
    private boolean paused = false;
    private boolean haveQuest = false;
    private SpriteBatch myBatch;
    private String amount;
    private Texture currencyTexture;
    List<Quest> questList = new ArrayList<>();
    Quest quest;


    /** Constructor. Loads animations, sets stats and creates hitbox */
    public MyHero(Painter painter, SpriteBatch batch, Class templateClass){
        super(painter, batch);

        idleAnimationRight = templateClass.idleAnimationRight;
        idleAnimationLeft = templateClass.idleAnimationLeft;
        runAnimationRight = templateClass.runAnimationRight;
        runAnimationLeft = templateClass.runAnimationLeft;
        animation = idleAnimationRight;
        currencyTexture = new Texture("item/currency.png");

        maxHealth = 70;
        maxMana = 20;
        frameCounter=0;
        health = templateClass.health;
        mana = templateClass.mana;
        defense = templateClass.defense;
        baseStrength = 4;
        strength = templateClass.strength;
        exp = 0;
        bones = 10;
        level = 1;
        reqExp = 1;
        movementSpeed = templateClass.movmentSpeed;
        name = templateClass.name;
        myBatch = new SpriteBatch();
        updateBones();
    }

    /** Sets Hero into the currently loaded level */
    public void setLevel(Level level){
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
    }

    /** Update our heroes position on the display when the position gets changed*/
    @Override
    public void updateNotPaused() {
        Point newPosition = new Point(this.position);

        if(!paused){
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                newPosition.y += movementSpeed;
                isRunningLeft();
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.S)){
                newPosition.y -= movementSpeed;
                isRunningLeft();
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                isLookingLeft = false;
                newPosition.x += movementSpeed;
                animation = runAnimationRight;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                isLookingLeft = true;
                newPosition.x -= movementSpeed;
                animation = runAnimationLeft;
            }
            else {
                if(isLookingLeft){
                    animation = idleAnimationLeft;
                }
                else {
                    animation = idleAnimationRight;
                }
            }
        }
        if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
            this.position = newPosition;
            this.hitBox.x = this.position.x;
            this.hitBox.y = this.position.y;
        }
    }

    /** Setter for the defense variable */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getBones() {
        return bones;
    }

    /**
     * gains one Bone which is the currency in the game
     * */
    public void gainBones() {
        bones++;
        updateBones();
    }

    public void addBones(int sellAmount){
        bones += sellAmount;
    }

    /**
     * substracts cost from the bones amount the hero has
     *
     * @param cost
     * */
    public void substractBones(int cost){
        this.bones -= cost;
        updateBones();
    }

    /**
     * draws the bone currency and the amount the hero has every frames
     * */
    public void updateBones(){
        myBatch.begin();
        BitmapFont font = new BitmapFont();
        font.setColor(0.5f, 1f, 0f, 1);
        font.draw(myBatch, amount = String.valueOf(bones), 20, 430);
        myBatch.draw(currencyTexture,35,417,15,15);
        myBatch.end();
    }

    /** Adds to the current defense variable */
    public void addDefense(int defense){
        this.defense += defense;
    }

    /** Setter for the strength variable */
    public void setStrength(int strength) {
        this.strength = strength + baseStrength;
    }

    /** Getter for the defense variable */
    public int getDefense() {
        return defense;
    }

    /** Getter for the mana variable */
    public int getMana() {
        return mana;
    }

    /** Getter for the maxHealth variable */
    public int getMaxHealth() {
        return maxHealth;
    }

    /** Getter for the maxMana variable */
    public int getMaxMana() {
        return maxMana;
    }

    /** Adder for the movementSpeed variable */
    public void addMovement(float movementSpeed){
        this.movementSpeed += movementSpeed;
    }

    /** Add for the strength variable */
    public void addPowerUp(float strength){
        this.strength += strength;
    }
    /** Adder for the health variable */
    public void addHealth(int health) {
        if (getMaxHealth() - getHealth() > health) {
            this.health += health;
        } else {
            this.health += getMaxHealth() - getHealth();
        }
    }

    /** Adder for the mana variable */
    public void addMana(int mana) {
        if (getMaxMana() - getMana() > mana) {
            this.mana += mana;
        } else {
            this.mana += getMaxMana() - getMana();
        }
    }

    /** removes value from the mana variable */
    public void removeMana(int manaToRemove) {
        this.mana -= manaToRemove;
    }

    /** Sets the game to pause */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getLevel() {
        return level;
    }

    public void setHaveQuest(boolean haveQuest) {
        this.haveQuest = haveQuest;
    }

    /**
     * checks if gotten exp is enough to level up, if so, level up and set exp to the remaining exp,
     * else just add gotten exp to the exp
     *
     * @param expAmount, which we are adding to our exp
     * */
    public void gainExp(int expAmount){
        int expToLevelUp = getReqExp()-exp;
        logger.info("Erfahrungspunkte: "+exp);
        if(expAmount > 0){
            logger.info(expAmount+" Erfahrungspunkte erhalten!");
        }

        if(expAmount >= expToLevelUp){
            exp = expAmount - expToLevelUp;
            levelUp();
        }else {
            exp += expAmount;
        }
        logger.info("Erfahrungspunkte: "+exp);
    }

    /**
     * exponential calculation for our required exp to level up
     * */
    public int getReqExp() {
        reqExp = 50+level*level-(20+level)+50;
        return reqExp;
    }

    private void levelUp(){
        level++;
        logger.info("LEVEL AUFSTIEG! Level: " + level);
        maxHealth++;
        maxMana++;
        baseStrength++;
        strength++;
        addHealth(5);
        addMana(5);
        if(haveQuest){
            notifyObserver();
        }
    }

    @Override
    public void register(Quest quest) {
        questList.add(quest);
        setHaveQuest(true);
    }

    @Override
    public void unregister(Quest quest) {
        questList.remove(quest);
        if(questList.isEmpty()){
            setHaveQuest(false);
        }
    }

    @Override
    public void notifyObserver() {
        for(int i = 0; i < questList.size(); i++){
            if (questList.get(i).getLevelRequirements() <= level) {
                questList.get(i).update();
                unregister(questList.get(i));
                i--;
            }
        }
    }
}
