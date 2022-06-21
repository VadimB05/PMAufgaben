package item.armor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import character.hero.MyHero;
import com.badlogic.gdx.math.Rectangle;
import graphic.Painter;
import hud.Icon;
import tools.Point;

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
        setIcon(new Icon(hudPainter,hudBatch,
                new Point(565f,335f),
                getTexturePath()));
        shopItem = new Rectangle();
        shopItem.set( 350, 380, 40, 40);
        hudEquipment = new Rectangle();
        hudEquipment.set(545f, Gdx.graphics.getHeight()-395f,60,60);
    }
    @Override
    public void useItem(MyHero myHero) {
        myHero.addDefense(defense);
    }

}
