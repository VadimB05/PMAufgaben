package projectile;

import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import tools.Point;

public abstract class Projectile extends Entity {
    protected Point position;
    protected String texturePath;

    /**
     * A object that can be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */

    public Projectile(Painter painter, SpriteBatch batch, String texturePath) {
        super(painter, batch);
        this.texturePath = texturePath;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public String getTexturePath() {
        return texturePath;
    }
}
