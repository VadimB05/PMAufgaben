package item.potion;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import desktop.MyHero;
import graphic.Painter;
import item.Items;

public class ManaPotion extends Potion{

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public ManaPotion(Painter painter, SpriteBatch batch, String texturePath, String name) {
        super(painter, batch, texturePath, name,0,5);

    }

    @Override
    public int useItem(MyHero myHero) {
        if(myHero.getMaxMana()-myHero.getMana()>mana){
            return mana;
        }
        else {
            return myHero.getMaxMana()-myHero.getMana();
        }
    }
}
