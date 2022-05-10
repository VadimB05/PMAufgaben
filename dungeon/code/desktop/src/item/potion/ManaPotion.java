package item.potion;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.EntityController;
import desktop.MyHero;
import graphic.Painter;
import inventory.Equipment;
import item.Items;

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
        if(myHero.getMaxMana()-myHero.getMana()>mana){
            myHero.addMana(mana);
        }
        else {
            myHero.addMana(myHero.getMaxMana()-myHero.getMana());
        }
    }


}
