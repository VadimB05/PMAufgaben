package desktop;

import basiselements.Animatable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import level.tools.Coordinate;
import logging.InventoryFormatter;
import quest.Quest;
import tools.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class MyHero extends Animatable {
    private final Rectangle hitBox;
    private Animation animation;
    private Animation runAnimationRight;
    private Animation runAnimationLeft;
    private Animation idleAnimationRight;
    private Animation idleAnimationLeft;
    private Point position;
    private Level currentLevel;
    private boolean isLookingLeft = false;
    private int mana;
    private int health;
    private int defense;
    private int strength;
    private int maxMana;
    private int maxHealth;

    private boolean paused = false;
    Random attackChance;
    private int randomIntAttackChance;
    Logger logger;
    Quest quest;
    float movementSpeed = 0.2f;

    /** Constructor. Loads animations, sets stats and creates hitbox */
    public MyHero(Painter painter, SpriteBatch batch){
        super(painter, batch);
        List<String> idleAnimationRightList = new ArrayList<>();
        List<String> idleAnimationLeftList = new ArrayList<>();
        List<String> runAnimationRightList = new ArrayList<>();
        List<String> runAnimationLeftList = new ArrayList<>();
        idleAnimationRightList.add("character/knight/knight_m_idle_anim_f0.png");
        idleAnimationRightList.add("character/knight/knight_m_idle_anim_f1.png");
        idleAnimationRightList.add("character/knight/knight_m_idle_anim_f2.png");
        idleAnimationRightList.add("character/knight/knight_m_idle_anim_f3.png");
        idleAnimationRight = new Animation(idleAnimationRightList,8);
        idleAnimationLeftList.add("character/knight/knight_m_idle_anim_mirrored_f0.png");
        idleAnimationLeftList.add("character/knight/knight_m_idle_anim_mirrored_f1.png");
        idleAnimationLeftList.add("character/knight/knight_m_idle_anim_mirrored_f2.png");
        idleAnimationLeftList.add("character/knight/knight_m_idle_anim_mirrored_f3.png");
        idleAnimationLeft = new Animation(idleAnimationLeftList,8);
        runAnimationRightList.add("character/knight/knight_m_run_anim_f0.png");
        runAnimationRightList.add("character/knight/knight_m_run_anim_f1.png");
        runAnimationRightList.add("character/knight/knight_m_run_anim_f2.png");
        runAnimationRightList.add("character/knight/knight_m_run_anim_f3.png");
        runAnimationRight = new Animation(runAnimationRightList,8);
        runAnimationLeftList.add("character/knight/knight_m_run_anim_mirrored_f0.png");
        runAnimationLeftList.add("character/knight/knight_m_run_anim_mirrored_f1.png");
        runAnimationLeftList.add("character/knight/knight_m_run_anim_mirrored_f2.png");
        runAnimationLeftList.add("character/knight/knight_m_run_anim_mirrored_f3.png");
        runAnimationLeft = new Animation(runAnimationLeftList,8);
        animation = idleAnimationRight;

        maxHealth = 70;
        maxMana = 20;
	frameCounter=0;
        health = 30;
        mana = 10;
        defense = 0;
        baseStrength = 4;
	strength = baseStrength;
	exp = 0;
	level = 1;
	reqExp = 1;
        hitBox = new Rectangle();
	attackChance = new Random();
    }

    /** Sets Hero into the currently loaded level */
    public void setLevel(Level level){
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
    }

    /** Update our heroes position on the display when the position gets changed*/
    @Override
    public void update() {
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

    /** Getter for the position variable */
    @Override
    public Point getPosition() {
        return position;
    }

    /** Getter for the currently active animaton */
    @Override
    public Animation getActiveAnimation() {
        return animation;
    }

    /** Change our animation */
    public void isRunningLeft(){
        if(isLookingLeft){
            animation = runAnimationLeft;
        }
        else{
            animation = runAnimationRight;
        }
    }

    /** Setter for the defense variable */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /** Adds to the current defense variable */
    public void addDefense(int defense){
        this.defense += defense;
    }

    /** Setter for the strength variable */
    public void setStrength(int strength) {
        this.strength = strength + baseStrength;
    }

    /** Getter for the strength variable */
    public int getStrength() {
        return strength;
    }

    /** Getter for the defense variable */
    public int getDefense() {
        return defense;
    }

    /** Getter for the health variable */
    public int getHealth() {
        return health;
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

    /** Getter for the movementSpeed variable */
    public float getMovement(){
        return movementSpeed;
    }

    /** Adder for the movementSpeed variable */
    public void addMovement(float movementSpeed){
        this.movementSpeed += movementSpeed;
    }

    /** Adder for the health variable */
    public void addHealth(int health) {
        if (getMaxHealth() - getHealth() > health) {
		this.health += health;
	} else {
		this.health += getMaxHealth() - getHealth();
	}
    }

    /** Setter for the health variable */
    public void setHealth(int health) {
        this.health = health;
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

    /** Getter for the hitbox*/
    public Rectangle getHitBox() {
        return hitBox;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
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
        return 50+level*level-(20+level)+50;
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
        checkQuestLevel();
    }

    private void checkQuestLevel() {
        if(quest.getLevelRequirements()==level){
            quest.update();
        }
    }
}
