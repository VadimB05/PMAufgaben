package character.monster;

import character.Character;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.Random;

public class Monster extends Character {
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
    public Monster(Painter painter, SpriteBatch batch, Variant type) {
        super(painter, batch);
        this.runAnimationLeft=type.runAnimationLeft;
        this.runAnimationRight=type.runAnimationRight;
        this.idleAnimationLeft=type.idleAnimationLeft;
        this.idleAnimationRight=type.idleAnimationRight;
        this.name = type.name;
        this.health = type.health;
        this.strength = type.strength;
        this.exp = type.exp;
        inCombat = false;
        setMovementSpeed(type.movementSpeed);
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

    /** Setter for the movementSpeed variable */
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /** Sets the character into combat mode */
    public void setInCombat(boolean inCombat) {
        this.inCombat = inCombat;
    }
}

