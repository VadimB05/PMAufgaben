package desktop;

import basiselements.Animatable;
import character.Monster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import level.tools.Coordinate;
import logging.InventoryFormatter;
import tools.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class MyHero extends Animatable {
    private final Rectangle hitBox;
    private Animation animation, runAnimationRight,runAnimationLeft,idleAnimationRight, idleAnimationLeft;
    private Point position;
    private Level currentLevel;
    private boolean isLookingLeft = false;
    private int mana,health,defense,strength,maxMana,maxHealth,frameCounter;
    private boolean paused = false;
    Random attackChance;
    private int randomIntAttackChance;
    Logger logger;

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

        logger = Logger.getLogger(this.getClass().getName());
        logger.setUseParentHandlers(false);
        ConsoleHandler handlerMain = new ConsoleHandler();

        handlerMain.setLevel(java.util.logging.Level.INFO);
        handlerMain.setFormatter(new InventoryFormatter("Hero Logger"));
        logger.setLevel(java.util.logging.Level.INFO);
        fixHandler();
        logger.addHandler(handlerMain);
        logger.setUseParentHandlers(false);

        maxHealth = 70;
        maxMana = 20;
        frameCounter=0;
        health = 30;
        mana = 10;
        defense = 0;
        strength = 4;

        hitBox = new Rectangle();
        attackChance = new Random();
    }



    public void setLevel(Level level){
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
    }

    /** Update our heroes position on the display when the position gets changed*/
    @Override
    public void update() {
        Point newPosition = new Point(position);
        float movementSpeed = 0.2f;

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

    @Override
    public Point getPosition() {
        return position;
    }

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

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void addDefense(int defense){
        this.defense += defense;
    }

    public void setStrength(int strength) {
        this.strength = 4;
        this.strength += strength;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addMana(int mana) {
        this.mana += mana;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void attack(Monster monster){
        randomIntAttackChance = attackChance.nextInt(10);
        if(randomIntAttackChance>=2) {
            logger.info("Monster Leben vorher: "+ monster.getHealth());
            monster.setHealth(monster.getHealth() - getStrength());
            monster.throwback(this);
            logger.info("Monster Leben nachher: "+ monster.getHealth());
        }else{
            logger.info("Angriff fehlgeschlagen");
        }
    }

    public void addFrameCounter(){
        frameCounter++;
    }

    public int getFrameCounter() {
        return frameCounter;
    }

    public void resetFrameCounter(){
        this.frameCounter=0;
    }

    /**
     * Checks if the attack was successful with a random int generator, calls method throwback()
     * and adjusts the life
     * */
    public void getAttacked(Monster monster){
        randomIntAttackChance = attackChance.nextInt(10);
        if( randomIntAttackChance>=4 && defense<monster.getStrength()){
            setHealth(getHealth()-(monster.getStrength()-defense));
            throwback(monster);
        }else{
            logger.info("Angriff vom Monster fehlgeschlagen");
        }
    }

    public void fixHandler(){
        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }
    }

    /**
     * Checks from which direction the monster is attacking and trys to throw the hero in the opposite direction,
     * depending if the tiles are accessible
     * */
    public void throwback(Monster monster){
        Point throwbackPosition = position;
        if(position.x-monster.getPosition().x >=0){
            throwbackPosition.x += 1;
            for(int i=0;i<10;i++){
                if (currentLevel.getTileAt(throwbackPosition.toCoordinate()).isAccessible()) {
                    this.position = throwbackPosition;
                }else {
                    throwbackPosition.x -= 0.1;
                }
            }
        }else{
            throwbackPosition.x -= 1;
            for(int i=0;i<10;i++){
                if (currentLevel.getTileAt(throwbackPosition.toCoordinate()).isAccessible()) {
                    this.position = throwbackPosition;
                }else {
                    throwbackPosition.x += 0.1;
                }
            }
        }
        if(position.y-monster.getPosition().y >=0){
            throwbackPosition.y += 1;
            for(int i=0;i<10;i++){
                if (currentLevel.getTileAt(throwbackPosition.toCoordinate()).isAccessible()) {
                    this.position = throwbackPosition;
                }else {
                    throwbackPosition.y -= 0.1;
                }
            }
        }else {
            throwbackPosition.y -= 1;
            for(int i=0;i<10;i++){
                if (currentLevel.getTileAt(throwbackPosition.toCoordinate()).isAccessible()) {
                    this.position = throwbackPosition;
                }else {
                    throwbackPosition.y += 0.1;
                }
            }
        }
    }
}
