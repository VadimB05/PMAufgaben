package trap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import java.util.ArrayList;
import java.util.List;

public class Hole extends Trap {
    private  Animation holeAnimation;

    /** Constructor. Loads the animation and sets damage */
    public Hole(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        List<String> holeAnimationList = new ArrayList<>();
        holeAnimationList.add("item/hole.png");
        holeAnimationList.add("item/hole2.png");
        holeAnimation = new Animation(holeAnimationList, 4);
        damage = 10;

    }

    @Override
    public Animation getActiveAnimation() {
        return holeAnimation;
    }

}
