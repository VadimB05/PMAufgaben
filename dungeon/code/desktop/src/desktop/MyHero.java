package desktop;

import basiselements.Animatable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;
import java.util.List;

public class MyHero extends Animatable {
    private final Rectangle hitBox;
    private Animation animation, runAnimationRight,runAnimationLeft,idleAnimationRight, idleAnimationLeft;
    private Point position;
    private Level currentLevel;
    private boolean isLookingLeft = false;
    private int mana,health,defense,strength,maxMana,maxHealth;

    private boolean paused = false;

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

        health = 30;
        mana = 10;
        defense = 0;
        strength = 4;
        hitBox = new Rectangle();
    }



    public void setLevel(Level level){
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
    }

    /** Update our heroes position on the display when the position gets changed*/
    @Override
    public void update() {
        Point newPosition = new Point(this.position);
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



}
