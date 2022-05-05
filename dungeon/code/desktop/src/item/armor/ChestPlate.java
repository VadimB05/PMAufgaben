package item.armor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import desktop.MyHero;
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
    public ChestPlate(Painter painter, SpriteBatch batch, String texturePath, String name, int defense) {
        super(painter, batch, texturePath, name, defense);
    }
    @Override
    public int useItem(MyHero myHero) {
        return 0;
    }
}
