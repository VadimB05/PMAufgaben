package item;

import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import desktop.MyHero;
import graphic.HUDPainter;
import graphic.Painter;
import hud.Icon;
import level.elements.Level;
import tools.Point;



public abstract class Items extends Entity {
    private final Rectangle hitBox;
    protected SpriteBatch hudBatch;
    protected HUDPainter hudPainter;
    protected Icon icon;
    protected String texturePath;
    protected Point position;
    protected String name;
    protected boolean pickedUp = false;


    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter   Painter that draws this object
     * @param batch       SpriteBatch to draw on
     * @param texturePath   Path of the texture
     * @param name  Name of the Object
     */
    public Items(Painter painter, SpriteBatch batch, String texturePath, String name) {
        super(painter, batch);
        this.texturePath = texturePath;
        this.name = name;
        hitBox = new Rectangle();
        hudPainter = new HUDPainter();
        hudBatch = new SpriteBatch();
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public String getTexturePath() {
        return texturePath;
    }

    public void setLevel(Level level){
        position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
        //position = level.getStartTile().getCoordinate().toPoint();
    }

    public void setPosition(Point position) {
        this.position = position;
        hitBox.set(position.x,position.y,1f,1f);
    }

    public String getName() {
        return name;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public abstract void useItem(MyHero myHero);

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public boolean collide(MyHero myHero) {
        return myHero.getHitBox().overlaps(this.hitBox);
    }
}
