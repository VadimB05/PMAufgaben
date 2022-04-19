package desktop;

import basiselements.Animatable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;
import java.util.List;

public class MyHero extends Animatable {
    private Animation idleAnimation;
    private Point position;
    private Level currentLevel;

    public MyHero(Painter painter, SpriteBatch batch){
        super(painter, batch);
        List<String> animation = new ArrayList<>();
        animation.add("character/knight/knight_m_idle_anim_f0.png");
        animation.add("character/knight/knight_m_idle_anim_f1.png");
        idleAnimation = new Animation(animation,8);
    }



    public void setLevel(Level level){
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
    }

    @Override
    public void update() {
        Point newPosition = new Point(this.position);
        float movementSpeed = 0.1f;

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            newPosition.y += movementSpeed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            newPosition.y -= movementSpeed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            newPosition.x += movementSpeed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            newPosition.x -= movementSpeed;
        }

        if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
            this.position = newPosition;
        }
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Animation getActiveAnimation() {
        return idleAnimation;
    }
}
