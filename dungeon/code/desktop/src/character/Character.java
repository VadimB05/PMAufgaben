package character;

import basiselements.Animatable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Character extends Animatable {
    protected Animation animation, runAnimationRight,runAnimationLeft,idleAnimationRight, idleAnimationLeft;
    protected List<String> idleAnimationRightList = new ArrayList<>();
    protected List<String> idleAnimationLeftList = new ArrayList<>();
    protected List<String> runAnimationRightList = new ArrayList<>();
    protected List<String> runAnimationLeftList = new ArrayList<>();
    private boolean isLookingLeft = false;
    protected float movementSpeed;
    private Level currentLevel;
    private Point position;
    private Point newPosition;
    private int upperboundMovement = 5;
    Random moving = new Random();
    private int randomIntMovement = moving.nextInt(upperboundMovement);
    int movementCounter = 0;

    public Character(Painter painter, SpriteBatch batch) {
        super(painter, batch);
    }

    public void setLevel(Level level){
        currentLevel = level;
        position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

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

    @Override
    public void update() {
        animation = getActiveAnimation();
        try {
            newPosition = randomMovement();
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.position = newPosition;
            }
        } catch (Exception e) {

        }
    }

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

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}

