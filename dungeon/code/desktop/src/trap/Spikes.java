package trap;

import basiselements.Animatable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;
import java.util.List;

public class Spikes extends Animatable {
    private Animation spikesAnimation;
    private Point position;
    private Level currentLevel;

    /**
     * Must be implemented for all objects that should be controlled by the <code>EntityController
     * </code>.
     *
     * @param painter Painter that draws this object
     * @param batch   Batch to draw on
     */
    public Spikes(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        List<String> spikesAnimationList = new ArrayList<>();
        spikesAnimationList.add("item/floor_spikes_anim_f0.png");
        spikesAnimationList.add("item/floor_spikes_anim_f1.png");
        spikesAnimationList.add("item/floor_spikes_anim_f2.png");
        spikesAnimationList.add("item/floor_spikes_anim_f3.png");
        spikesAnimation = new Animation(spikesAnimationList, 4);

    }

    public void setLevel(Level level){
        currentLevel = level;
        position = level.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public Animation getActiveAnimation() {
        return spikesAnimation;
    }

    @Override
    public Point getPosition() {
        return position;
    }
}
