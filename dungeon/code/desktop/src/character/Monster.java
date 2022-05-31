package character;

import basiselements.Animatable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import desktop.MyHero;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Monster extends Animatable {
    protected Animation animation;
    protected Animation runAnimationRight;
    protected Animation runAnimationLeft;
    protected Animation idleAnimationRight;
    protected Animation idleAnimationLeft;
    protected List<String> idleAnimationRightList = new ArrayList<>();
    protected List<String> idleAnimationLeftList = new ArrayList<>();
    protected List<String> runAnimationRightList = new ArrayList<>();
    protected List<String> runAnimationLeftList = new ArrayList<>();
    private boolean isLookingLeft = false;
    protected float movementSpeed;
    private int health;
    private int strength;
    private int frameCounter;
    private int exp;
    private Rectangle hitBox;
    private Level currentLevel;
    private Point position;
    private Point newPosition;
    private int upperboundMovement = 5;
    Random moving = new Random();
    private int randomIntMovement = moving.nextInt(upperboundMovement);
    int movementCounter = 0;
    private boolean inCombat;

    /**
     * Non-playable character that gets drawn in the dungeon
     *
     * @param painter   Painter that draws this object
     * @param batch     SpriteBatch to draw on
     */
    public Monster(Painter painter, SpriteBatch batch, int health, int strength, int exp) {
        super(painter, batch);
        this.health = health;
        this.strength = strength;
        this.exp = exp;
        hitBox = new Rectangle();
        inCombat = false;
    }

    /**
     * Sets character on random position
     *
     * @param level     Current level
     */
    public void setLevel(Level level){
        currentLevel = level;
        position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

    /**
     * Creates random movement for character,
     * either going in one of the four directions or standing still for a moment.
     *
     * @return new position of character
     */
    public Point randomMovement() {
        Point newPosition = new Point(this.position);

        if(movementCounter == 30) {
            movementCounter = 0;
            this.randomIntMovement = moving.nextInt(upperboundMovement);
            return position;
        }
        if(randomIntMovement == 0) {
            movementCounter++;
            isLookingLeft = false;
            animation = runAnimationRight;
            newPosition.x += movementSpeed;
        }
        if(randomIntMovement == 1) {
            movementCounter++;
            isLookingLeft = true;
            animation = runAnimationLeft;
            newPosition.x -= movementSpeed;
        }
        if(randomIntMovement == 2) {
            movementCounter++;
            isRunningLeft();
            newPosition.y += movementSpeed;
        }
        if(randomIntMovement == 3) {
            movementCounter++;
            isRunningLeft();
            newPosition.y -= movementSpeed;
        }
        if(randomIntMovement == 4) {
            movementCounter++;
            if(isLookingLeft) {
                animation = idleAnimationLeft;
            }
            else {
                animation = idleAnimationRight;
            }
            return position;
        }
        return newPosition;
    }

    /**
     * Executes every frame to update monster movement
     */
    @Override
    public void update() {
        animation = getActiveAnimation();
        if(!inCombat){
            newPosition = randomMovement();
        }else{
            if(isLookingLeft) {
                animation = idleAnimationLeft;
            }
            else {
                animation = idleAnimationRight;
            }
        }
        if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
            this.position = newPosition;
            hitBox.set(newPosition.x,newPosition.y,1f,1f);
        }
    }

    /**
     * Checks the direction the monster is facing for proper animation
     */
    public void isRunningLeft(){
        if(isLookingLeft){
            animation = runAnimationLeft;
        }
        else{
            animation = runAnimationRight;
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

    /** Setter for the movementSpeed variable */
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /** Checks if the hitbox of the hero overlaps with the hitbox of a monster */
    public boolean collide(MyHero myHero) {
        return myHero.getHitBox().overlaps(this.hitBox);
    }

    /** Getter for the health variable */
    public int getHealth() {
        return health;
    }

    /** Setter for the health variable */
    public void setHealth(int health) {
        this.health = health;
    }

    /** Getter for the exp variable */
    public int getExp() {
        return exp;
    }

    /** Getter for the frameCounter variable */
    public int getFrameCounter() {
        return frameCounter;
    }

    /** adder for the frameCounter variable */
    public void addFrameCounter() {
        frameCounter++;
    }

    /** resets the framecounter */
    public void resetFrameCounter() {
        this.frameCounter = 0;
    }

    /** Getter for the strength variable */
    public int getStrength() {
        return strength;
    }

    /** Checks if a monster is dead */
    public boolean checkMonsterDead(){
        return health<=0;
    }

    /** Sets the character into combat mode */
    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }

    /**
     * Checks from which direction the hero is attacking and trys to throw the monster in the opposite direction,
     * depending if the tiles are accessible
     *
     * @param hero, the hero that is attacking the monster
     * */
    public void throwback(MyHero hero){
        Point throwbackPosition = position;
        try {
            if (position.x - hero.getPosition().x >= 0 && !checkMonsterDead()) {
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
            if (position.y - hero.getPosition().y >= 0) {
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
        } catch (NullPointerException exception){
            throw exception;
        }
    }
}

