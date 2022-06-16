package character;

import basiselements.Animatable;
import character.monster.Monster;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import logging.InventoryFormatter;
import projectile.Projectile;
import tools.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public abstract class Character extends Animatable {
    protected Point position;
    protected float movementSpeed;
    protected Animation animation;
    protected Animation runAnimationRight;
    protected Animation runAnimationLeft;
    protected Animation idleAnimationRight;
    protected Animation idleAnimationLeft;
    protected List<String> idleAnimationRightList = new ArrayList<>();
    protected List<String> idleAnimationLeftList = new ArrayList<>();
    protected List<String> runAnimationRightList = new ArrayList<>();
    protected List<String> runAnimationLeftList = new ArrayList<>();
    protected Level currentLevel;
    private int randomIntAttackChance;
    protected boolean isLookingLeft = false;
    protected String name;
    protected int health;
    protected int strength;
    protected int exp;
    protected int frameCounter;
    protected Rectangle hitBox;
    Random attackChance;
    protected Logger logger;
    private boolean paused;

    /**
     * Must be implemented for all objects that should be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */
    public Character(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        hitBox = new Rectangle();
        attackChance = new Random();

        logger = Logger.getLogger(this.getClass().getName());
        logger.setUseParentHandlers(false);
        ConsoleHandler handlerMain = new ConsoleHandler();

        handlerMain.setLevel(java.util.logging.Level.INFO);
        handlerMain.setFormatter(new InventoryFormatter("Hero Logger"));
        logger.setLevel(java.util.logging.Level.INFO);
        fixHandler();
        logger.addHandler(handlerMain);
        logger.setUseParentHandlers(false);
    }

    /**
     * Getter for the position variable
     */
    @Override
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Getter for the currently active animaton
     */
    @Override
    public Animation getActiveAnimation() {
        return animation;
    }

    /**
     * Checks the direction the monster is facing for proper animation
     */
    public void isRunningLeft() {
        if (isLookingLeft) {
            animation = runAnimationLeft;
        } else {
            animation = runAnimationRight;
        }
    }

    /**
     * Getter for the health variable
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setter for the health variable
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Getter for the exp variable
     */
    public int getExp() {
        return exp;
    }

    /**
     * Getter for the hitbox
     */
    public Rectangle getHitBox() {
        return hitBox;
    }

    /**
     * Checks if the hitbox of the hero overlaps with the hitbox of a monster
     */
    public boolean collide(Character character) {
        return character.getHitBox().overlaps(this.hitBox);
    }


    /** Checks if the hitbox of a projectile overlaps with the hitbox of a monster */
    public boolean collide(Projectile projectile) {
        return projectile.getHitBox().overlaps(this.hitBox);
    }


    /** Getter for the frameCounter variable */
    public int getFrameCounter() {
        return frameCounter;
    }

    /**
     * Getter for the strength variable
     */
    public int getStrength() {
        return strength;
    }

    /**
     * adder for the frameCounter variable
     */
    public void addFrameCounter() {
        frameCounter++;
    }

    /**
     * resets the framecounter
     */
    public void resetFrameCounter() {
        this.frameCounter = 0;
    }

    /**
     * Checks if a character is dead
     */
    public boolean checkDead() {
        return health <= 0;
    }

    /**
     * removing doubled handler
     */
    private void fixHandler() {
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
    }

    /**
     * changing the health of the monster we are attacking
     *
     * @param character, the monster we are attacking
     */
    public void attack(Character character) {
        randomIntAttackChance = attackChance.nextInt(10);
        if (randomIntAttackChance >= 1) {
            logger.info(character.name + " Leben vorher: " + character.getHealth());
            character.setHealth(character.getHealth() - getStrength());
            //character.throwback(this);
            logger.info(character.name + " Leben nachher: " + character.getHealth());
        } else {
            logger.info(character.name + " Angriff fehlgeschlagen");
        }
    }

    /**
     * changing the health of the monster we are attacking
     *
     * @param projectile, the damage we are doing
     * */
    public void getAttacked(Projectile projectile){
        if(projectile.getDamage()>0) {
            logger.info(name + " Leben vorher: "+ getHealth());
            setHealth(getHealth() - projectile.getDamage());
            throwback(projectile);
            logger.info(name + " Leben nachher: "+ getHealth());
        }
    }

    /**
     * changing the health of the monster we are attacking
     *
     * @param damage, the damage we are doing
     * */
    public void getDamaged(int damage){
        setHealth(getHealth()-damage);
    }

    /**
     * Checks from which direction the monster is attacking and trys to throw the hero in the opposite direction,
     * depending if the tiles are accessible
     *
     * @param character, the monster that is attacking us
     */
    private void throwback(Projectile character){
        Point throwbackPosition = position;
        if (position.x - character.getPosition().x >= 0 && !checkDead()) {
            throwbackPosition.x += 1;
            for (int i = 0; i < 10; i++) {
                if (currentLevel.getTileAt(throwbackPosition.toCoordinate()).isAccessible()) {
                    this.position = throwbackPosition;
                } else {
                    throwbackPosition.x -= 0.1;
                }
            }
        } else {
            throwbackPosition.x -= 1;
            for (int i = 0; i < 10; i++) {
                if (currentLevel.getTileAt(throwbackPosition.toCoordinate()).isAccessible()) {
                    this.position = throwbackPosition;
                } else {
                    throwbackPosition.x += 0.1;
                }
            }
        }
        if (position.y - character.getPosition().y >= 0 && !checkDead()) {
            throwbackPosition.y += 1;
            for (int i = 0; i < 10; i++) {
                if (currentLevel.getTileAt(throwbackPosition.toCoordinate()).isAccessible()) {
                    this.position = throwbackPosition;
                } else {
                    throwbackPosition.y -= 0.1;
                }
            }
        } else {
            throwbackPosition.y -= 1;
            for (int i = 0; i < 10; i++) {
                if (currentLevel.getTileAt(throwbackPosition.toCoordinate()).isAccessible()) {
                    this.position = throwbackPosition;
                } else {
                    throwbackPosition.y += 0.1;
                }
            }
        }
    }

    public void setPaused(boolean status) {
        this.paused = status;
    }

    @Override
    public final void update() {
        if (!paused) {
            updateNotPaused();
        }
    }

    public abstract void updateNotPaused();

}
