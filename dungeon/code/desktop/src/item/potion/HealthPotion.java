package item.potion;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import item.Items;

public class HealthPotion extends Potion {

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public HealthPotion(Painter painter, SpriteBatch batch, String texturePath) {
        super(painter, batch, texturePath);

        setHeal(20);
    }


}
