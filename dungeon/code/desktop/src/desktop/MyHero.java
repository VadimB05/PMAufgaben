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

public class MyHero extends Animatable  {
    private Animation idleAnimation;
    private Animation runAnimation;
    private Point position;
    private Level currentLevel;
    private boolean isRunning;

    public MyHero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        // Erstellen einer ArrayList
        List<String> idleAnimation = new ArrayList<>();
        // Laden der Texturen für die Animation (relativen Pfad angeben)
        idleAnimation.add("character/knight/knight_m_idle_anim_f0.png");
        idleAnimation.add("character/knight/knight_m_idle_anim_f1.png");
        idleAnimation.add("character/knight/knight_m_idle_anim_f2.png");
        idleAnimation.add("character/knight/knight_m_idle_anim_f3.png");
        // Erstellen einer Animation, als Parameter wird die Liste mit den Texturen
        // und die Wartezeit (in Frames) zwischen den Wechsel der Texturen angegeben
        this.idleAnimation = new Animation(idleAnimation, 8);

        List<String> runAnimation = new ArrayList<>();
        runAnimation.add("character/knight/knight_m_run_anim_f0.png");
        runAnimation.add("character/knight/knight_m_run_anim_f1.png");
        runAnimation.add("character/knight/knight_m_run_anim_f2.png");
        runAnimation.add("character/knight/knight_m_run_anim_f3.png");
        this.runAnimation = new Animation(runAnimation, 4);
    }

    public void setLevel(Level level) {
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
    }

    @Override
    public void update() {
        // Temporären Point um den Held nur zu bewegen, wenn es keine Kollision gab
        Point newPosition = new Point(this.position);

        // Unser Held soll sich pro Schritt um 0.1 Felder bewegen.
        float movementSpeed = 0.1f;

        // if else einbinden
        // if (irgendein button input)
        //      isRunning = true;
        // else
        //      isRunning = false;

        // Wenn die Taste W gedrückt ist, bewege dich nach oben
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            isRunning = true;
            newPosition.y += movementSpeed;
        }

        // Wenn die Taste S gedrückt ist, bewege dich nach unten
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            isRunning = true;
            newPosition.y -= movementSpeed;
        }

        // Wenn die Taste D gedrückt ist, bewege dich nach rechts
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            isRunning = true;
            newPosition.x += movementSpeed;
        }

        // Wenn die Taste A gedrückt ist, bewege dich nach links
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            isRunning = true;
            newPosition.x -= movementSpeed;
        }

        // Wenn der übergebene Punkt betretbar ist, ist das nun die aktuelle Position
        if (currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
            this.position = newPosition;
        }
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Animation getActiveAnimation() {
        if(isRunning) {
            return runAnimation;
        }

        else {
            return idleAnimation;
        }
    }

    /*
    @Override
    public boolean removable() {
        return lebenspunkte == 0;
    }
     */
}
