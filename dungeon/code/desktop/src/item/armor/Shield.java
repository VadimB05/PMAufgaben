package item.armor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import item.Items;

public class Shield extends Armor {

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public Shield(Painter painter, SpriteBatch batch, String texturePath, String name) {
        super(painter, batch, texturePath, name);

        setDefense(2);
    }
}
