package trap;

import basiselements.Animatable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import desktop.MyHero;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

public abstract class Trap  extends Animatable {
    private final Rectangle hitBox;
    private long laststeptime;
    private Level currentLevel;
    protected Point position;
    protected int damage;

    /** Constructor. Loads animation and creates hitbox */
    public Trap(Painter painter, SpriteBatch batch){
        super(painter, batch);
        laststeptime = TimeUtils.millis();
        hitBox = new Rectangle();

    }

    /** Sets trap into random position of the level */
    public void setLevel(Level level){
        currentLevel = level;
        position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
    }

    /** Getter for the damage variable */
    public int getDamage(){
        return -damage;
    }

    /** Getter for the hitbox object */
    public Rectangle getHitBox(){
        return hitBox;
    }

    /** Getter for the position object */
    public Point getPosition(){
        return position;
    }

    /**
     * This function is for the  colliding with the trap.
     * There is also a Timer for the trap.
     * @param myHero
     * @return
     */
    public boolean collide(MyHero myHero) {
        if (TimeUtils.millis() - laststeptime > 1000) {
            if (myHero.getHitBox().overlaps(this.hitBox)) {
                laststeptime = TimeUtils.millis();
                return true;
            }
        }
        return false;
    }

}
