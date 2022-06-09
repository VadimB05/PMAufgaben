package item.potion;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import character.hero.MyHero;
import graphic.Painter;

public class ManaPotion extends Potion{

    /**
     * An object in the dungeon that can be drawn
     *
     * @param painter
     * @param batch       SpriteBatch to draw on
     * @param texturePath
     */
    public ManaPotion(Painter painter, SpriteBatch batch, String texturePath, String name, int health, int mana) {
        super(painter, batch, texturePath, name,health,mana);

    }

    @Override
    public void useItem(MyHero myHero) {
        myHero.addMana(mana);
    }


}
