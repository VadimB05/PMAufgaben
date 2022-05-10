package item.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.EntityController;
import desktop.MyHero;
import graphic.Painter;
import inventory.Equipment;
import item.Items;

public class Staff extends Weapons {

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public Staff(Painter painter, SpriteBatch batch, String texturePath, String name, int damage) {
        super(painter, batch, texturePath, name, damage);
    }
    @Override
    public void useItem(MyHero myHero) {
        myHero.setStrength(damage);
    }


}
