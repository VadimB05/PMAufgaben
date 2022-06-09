package projectile;

import basiselements.Animatable;
import character.Character;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.badlogic.gdx.math.Rectangle;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Projectile extends Animatable {
    protected Point position;
    protected Point newPosition;
    protected String texturePath;
    protected Level currentLevel;
    protected int flyingCounter = 0;
    protected boolean isFlyingUp = false;
    protected boolean isFlyingDown = false;
    protected boolean isFlyingLeft = false;
    protected boolean isFlyingRight = false;
    protected float flyingSpeed;
    protected int flyingDistance;
    protected List<String> flyingAnimationList = new ArrayList<>();
    protected Animation animation;
    protected Rectangle hitBox;
    protected int damage;

    /**
     * A object that can be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */

    public Projectile(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        hitBox = new Rectangle();
    }

    @Override
    public Point getPosition() {
        return position;
    }

    /** Getter for the hitbox*/
    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setLevel(Level level){
        currentLevel = level;
        hitBox.set(position.x,position.y,1f,1f);
    }

    @Override
    public void update() {
        if(isFlyingUp) {
            newPosition = moveUp();
        }
        if(isFlyingDown) {
            newPosition = moveDown();
        }
        if(isFlyingLeft) {
            newPosition = moveLeft();
        }
        if(isFlyingRight) {
            newPosition = moveRight();
        }
    }

    public Point moveUp() {
        Point newPosition = new Point(this.position);
        if(flyingCounter <= this.flyingDistance) {
            flyingCounter++;
            newPosition.y += flyingSpeed;
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                
                this.position = newPosition;
                hitBox.set(newPosition.x,newPosition.y,1f,1f);
            }
        }
        else {
            isFlyingUp = false;
            damage=0;
        }
        return position;
    }

    public Point moveDown() {
        Point newPosition = new Point(this.position);
        if(flyingCounter <= this.flyingDistance) {
            flyingCounter++;
            newPosition.y -= flyingSpeed;
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.position = newPosition;
                hitBox.set(newPosition.x,newPosition.y,1f,1f);
            }
        }
        else {
            isFlyingDown = false;
            damage=0;
        }
        return position;
    }

    public Point moveLeft() {
        Point newPosition = new Point(this.position);
        if(flyingCounter <= this.flyingDistance) {
            flyingCounter++;
            newPosition.x -= flyingSpeed;
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.position = newPosition;
                hitBox.set(newPosition.x,newPosition.y,1f,1f);
            }
        }
        else {
            isFlyingLeft = false;
            damage=0;
        }
        return position;
    }

    public Point moveRight() {
        Point newPosition = new Point(this.position);
        if(flyingCounter <= this.flyingDistance) {
            flyingCounter++;
            newPosition.x += flyingSpeed;
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.position = newPosition;
                hitBox.set(newPosition.x,newPosition.y,1f,1f);
            }
        }
        else {
            isFlyingRight = false;
            damage=0;
        }
        return position;
    }

    public void setFlyingSpeed(float flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    public int getDamage() {
        return damage;
    }

    public void setFlyingDirectionUp() {
        isFlyingUp = true;
    }

    public void setFlyingDirectionDown() {
        isFlyingDown = true;
    }

    public void setFlyingDirectionLeft() {
        isFlyingLeft = true;
    }

    public void setFlyingDirectionRight() {
        isFlyingRight = true;
    }
}
