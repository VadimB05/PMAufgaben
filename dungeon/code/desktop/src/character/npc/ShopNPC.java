package character.npc;

import character.Character;
import character.hero.MyHero;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import hud.ShopWindow;
import hud.Window;
import level.elements.Level;

public class ShopNPC extends Character {
    private SpriteBatch myBatch;
    Window shopWindow;
    /**
     * Must be implemented for all objects that should be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */
    public ShopNPC(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        idleAnimationRightList.add("character/npc/shop_idle.png");
        animation = new Animation(idleAnimationRightList,8);
        myBatch = new SpriteBatch();
        shopWindow = new ShopWindow();
    }

    @Override
    public void updateNotPaused() {

    }

    public void checkNearShop(MyHero hero){
        if(doesCollide(hero)){
            drawShop();
        }
    }

    /**
     * Checks if our hero is colliding with questNPC
     * */
    private boolean doesCollide(MyHero hero){
        return hero.getHitBox().overlaps(getHitBox());
    }

    /**
     * Sets character on random position
     *
     * @param level     Current level
     */
    public void setLevel(Level level){
        currentLevel = level;
        position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
        hitBox.set(position.x,position.y,1f,1f);
    }

    private void drawShop() {
        myBatch.begin();
        BitmapFont font = new BitmapFont();
        myBatch.draw(shopWindow.getWindow(), 350, 250, 280, 200);
        font.setColor(0f, 0f, 0f, 1);
        font.draw(myBatch, "Shop", 355, 445);
        myBatch.end();
    }
}
