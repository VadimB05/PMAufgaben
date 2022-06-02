package projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

public class Stone extends Projectile {

    /**
     * A object that can be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter     Painter that draws this object
     * @param batch       Batch to draw on
     */
    public Stone(Painter painter, SpriteBatch batch) {
        super(painter, batch, "projectile/projectile_stone.png");
    }
}
