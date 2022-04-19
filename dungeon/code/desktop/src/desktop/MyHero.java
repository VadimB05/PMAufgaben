package desktop;

import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

public class MyHero extends Entity {
    private String texturePath;
    private Point position;
    private Level currentLevel;

    public MyHero(Painter painter, SpriteBatch batch){
        super(painter, batch);
        texturePath = "character/knight/knight_m_idle_anim_f0.png";
    }

    public void setLevel(Level level){
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
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
