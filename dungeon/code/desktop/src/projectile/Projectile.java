package projectile;

import basiselements.Animatable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import tools.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Projectile extends Animatable {
    protected Point position;
    protected String texturePath;
    protected boolean isFlyingUp;
    protected float flyingSpeed;
    protected int flyingDistance;
    protected int flyingCounter = 0;
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

    @Override
    public void update() {

        if(flyingCounter == flyingDistance) {
            isFlyingUp = false;
            flyingCounter = 0;
        }

        if(isFlyingUp) {
            this.position = moveUp();
        }
    }

    public Point moveUp() {
        Point newPosition = new Point(this.position);
        isFlyingUp = true;
        flyingCounter++;
        newPosition.y += flyingSpeed;
        return newPosition;
    }

    public void moveDown() {
        Point newPosition = new Point(this.position);
        newPosition.y -= flyingSpeed;
        this.position = newPosition;
    }

    public void moveLeft() {
        Point newPosition = new Point(this.position);
        newPosition.x -= flyingSpeed;
        this.position = newPosition;
    }

    public void moveRight() {
        Point newPosition = new Point(this.position);
        newPosition.x += flyingSpeed;
        this.position = newPosition;
    }
}
