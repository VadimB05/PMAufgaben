package projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import tools.Point;

public class SpikedBall extends Projectile {
    /**
     * A object that can be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */
    public SpikedBall(Painter painter, SpriteBatch batch, Point heroPosition) {
        super(painter, batch);
        flyingAnimationList.add("projectile/projectile_spikedBall.png");
        flyingAnimationList.add("projectile/projectile_spikedBall.png");
        animation = new Animation(flyingAnimationList, 8);
        this.position = heroPosition;
        this.flyingSpeed = 0.3f;
        this.flyingDistance = 4;
        this.damage = 3;
    }

    @Override
    public Animation getActiveAnimation() {
        return animation;
    }
}
