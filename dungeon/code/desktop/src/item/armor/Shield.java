package item.armor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.EntityController;
import desktop.MyHero;
import graphic.Painter;
import inventory.Equipment;
import item.Items;

public class Shield extends Armor {

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public Shield(Painter painter, SpriteBatch batch, String texturePath, String name, int defense) {
        super(painter, batch, texturePath, name, defense);
    }
    @Override
    public void useItem(MyHero myHero) {
        myHero.addDefense(defense);
    }

}
