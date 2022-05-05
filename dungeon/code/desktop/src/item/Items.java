package item;

import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.EntityController;
import desktop.MyHero;
import graphic.Painter;
import inventory.Equipment;
import level.elements.Level;
import tools.Point;


public abstract class Items extends Entity {

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


    public abstract void useItem(MyHero myHero);


}
