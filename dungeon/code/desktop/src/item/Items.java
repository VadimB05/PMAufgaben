package item;

import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

public abstract class Items extends Entity {
    private Painter painter;
    protected String texturePath;
    protected Point position;

    protected String name;


    /**
     * An object in the dungeon that can be drawn
     *
     * @param batch SpriteBatch to draw on
     */
    public Items(Painter painter, SpriteBatch batch, String texturePath, String name) {
        super(painter, batch);
        this.painter = painter;
        this.texturePath = texturePath;
        this.name = name;
    }

    public Painter getPainter() {
        return painter;
    }

    public void draw() {
        getPainter().drawWithScaling(0.5f,1f, getTexturePath(), getPosition(), getBatch());
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
        //position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
        position = level.getStartTile().getCoordinate().toPoint();
    }

    public String getName() {
        return name;
    }
}
