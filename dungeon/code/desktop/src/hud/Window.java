package hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public abstract class Window {
    protected Texture window;
    protected final Texture background;

    /**
     * Creates a window and background window that is see through
     * */
    public Window(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        this.window = new Texture(pixmap);

        pixmap = new Pixmap(24, 24, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0.5f);
        pixmap.fill();
        this.background = new Texture(pixmap);

        pixmap.dispose();
    }

    /** Getter for the window texture */
    public Texture getWindow() {
        return window;
    }

    /** Getter for the background texture */
    public Texture getBackground() {
        return background;
    }
}
