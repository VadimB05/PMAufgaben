package item.armor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

public class ChestPlate extends Armor{

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     * @param name
     */
    public ChestPlate(Painter painter, SpriteBatch batch, String texturePath, String name) {
        super(painter, batch, texturePath, name);

        setDefense(10);
    }
}
