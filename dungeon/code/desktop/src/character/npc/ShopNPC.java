package character.npc;

import character.Character;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.elements.Level;

public class ShopNPC extends Character {
    /**
     * Must be implemented for all objects that should be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */
    public ShopNPC(Painter painter, SpriteBatch batch) {
        super(painter, batch);
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
}
