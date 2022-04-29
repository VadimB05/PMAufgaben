package item.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import item.Items;


public abstract class Weapons extends Items {
    protected Painter painter;
    protected int damage;

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter   Painter that draws this object
     * @param batch       SpriteBatch to draw on
     * @param texturePath   Path of the texture
     * @param name  Name of the Object
     */
    public Weapons(Painter painter, SpriteBatch batch, String texturePath, String name, int damage) {
        super(painter, batch, texturePath, name,0,0);
        this.painter = painter;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }


    public Painter getPainter() {
        return painter;
    }

    public void draw() {
        getPainter().drawWithScaling(0.5f,1f, getTexturePath(), getPosition(), getBatch());
    }
}
