package item.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import item.Items;
import tools.Point;

public class Sword extends Items {


    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public Sword(Painter painter, SpriteBatch batch, String texturePath) {
        super(painter, batch, texturePath);
    }

}
