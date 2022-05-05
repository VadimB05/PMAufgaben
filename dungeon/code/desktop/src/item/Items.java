package item;

import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import desktop.MyHero;
import graphic.Painter;
import level.elements.Level;
import tools.Point;


public abstract class Items extends Entity {

    protected String texturePath;
    protected Point position;
    protected String name;
    protected boolean pickedUp = false;
    protected int health;
    protected int mana;


    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter   Painter that draws this object
     * @param batch       SpriteBatch to draw on
     * @param texturePath   Path of the texture
     * @param name  Name of the Object
     */
    public Items(Painter painter, SpriteBatch batch, String texturePath, String name, int health, int mana) {
        super(painter, batch);
        this.texturePath = texturePath;
        this.name = name;
        this.health = health;
        this.mana = mana;
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
        //position = level.getStartTile().getCoordinate().toPoint();
    }

    public void setPosition(Point position) {
        this.position = position;
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


    public abstract int useItem(MyHero myHero);

}
