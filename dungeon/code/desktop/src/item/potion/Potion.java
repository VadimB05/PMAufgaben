package item.potion;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import item.Items;

public abstract class Potion extends Items {

    private Painter painter;
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
    public Potion(Painter painter, SpriteBatch batch, String texturePath, String name, int health, int mana) {
        super(painter, batch, texturePath, name);
        this.painter = painter;
        this.health = health;
        this.mana = mana;
    }

    /** Getter for the painter object */
    public Painter getPainter() {
        return painter;
    }

    @Override
    public void draw() {
        getPainter().drawWithScaling(1f,1f, getTexturePath(), getPosition(), getBatch());
    }

}
