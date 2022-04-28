package item.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import item.Items;


public abstract class Weapons extends Items {
    protected int damage;
    protected int strength;
    protected int durability;

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public Weapons(Painter painter, SpriteBatch batch, String texturePath, String name) {
        super(painter, batch, texturePath, name);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}
