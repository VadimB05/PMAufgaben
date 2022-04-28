package item.potion;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import item.Items;

public abstract class Potion extends Items {

    protected int heal;
    protected int mana;

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public Potion(Painter painter, SpriteBatch batch, String texturePath, String name) {
        super(painter, batch, texturePath, name);
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
