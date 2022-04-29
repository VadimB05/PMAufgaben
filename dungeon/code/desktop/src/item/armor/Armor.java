package item.armor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import item.Items;

public abstract class Armor extends Items {
    protected Painter painter;
    protected int defense;

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter   Painter that draws this object
     * @param batch       SpriteBatch to draw on
     * @param texturePath   Path of the texture
     * @param name  Name of the Object
     */
    public Armor(Painter painter, SpriteBatch batch, String texturePath, String name,int defense) {
        super(painter, batch, texturePath, name,0,0);
        this.painter = painter;
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }


    public Painter getPainter() {
        return painter;
    }

    @Override
    public void draw() {
        getPainter().drawWithScaling(1f,1f, getTexturePath(), getPosition(), getBatch());
    }
}
