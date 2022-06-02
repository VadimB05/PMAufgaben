package projectile;

import basiselements.Animatable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    /**
     * A object that can be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */

    public Projectile(Painter painter, SpriteBatch batch) {
        super(painter, batch);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    public void setLevel(Level level){
        currentLevel = level;
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
            this.position = newPosition;
        }
        else {
            isFlyingUp = false;
        }
        return position;

        //if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
        //    return position;
        //}
        //return newPosition;
    }

    public Point moveDown() {
        Point newPosition = new Point(this.position);
        if(flyingCounter <= this.flyingDistance) {
            flyingCounter++;
            newPosition.y -= flyingSpeed;
            this.position = newPosition;
        }
        else {
            isFlyingDown = false;
        }
        return position;
    }

    public Point moveLeft() {
        Point newPosition = new Point(this.position);
        if(flyingCounter <= this.flyingDistance) {
            flyingCounter++;
            newPosition.x -= flyingSpeed;
            this.position = newPosition;
        }
        else {
            isFlyingLeft = false;
        }
        return position;
    }

    public Point moveRight() {
        Point newPosition = new Point(this.position);
        if(flyingCounter <= this.flyingDistance) {
            flyingCounter++;
            newPosition.x += flyingSpeed;
            this.position = newPosition;
        }
        else {
            isFlyingRight = false;
        }
        return position;
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
