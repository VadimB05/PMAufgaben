package item;

import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

public abstract class Items extends Entity {
    private Painter painter;
    public String texturePath;
    public Point position;

    private Level currentLevel;

    /**
     * An object in the dungeon that can be drawn
     *
     * @param batch SpriteBatch to draw on
     */
    public Items(Painter painter, SpriteBatch batch, String texturePath) {
        super(painter, batch);
        this.painter = painter;
        this.texturePath = texturePath;
    }

    public Painter getPainter() {
        return painter;
    }

    public void draw() {
        getPainter().draw(getTexturePath(), getPosition(), getBatch());
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
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
    }


}
