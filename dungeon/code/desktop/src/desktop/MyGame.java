package desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import controller.MainController;
import hud.Icon;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import tools.Point;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MyGame extends MainController {
    private Label levelLabel;
    private MyHero hero;
    private int levelCounter = 0;



    @Override
    protected void setup() {
        levelAPI.setGenerator(new LevelLoader());
        hero = new MyHero(painter,batch);
        // load the first level
        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            System.out.println(
                    "Es konnte kein Level geladen werden, bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }
        camera.follow(hero);
        entityController.add(hero);
        hudController.add(new Icon(hudPainter,hudBatch,new Point(0f,0f)));
    }

    @Override
    protected void beginFrame() {}

    @Override
    protected void endFrame() {
        if(levelAPI.getCurrentLevel().isOnEndTile(hero)){
            try {
                levelAPI.loadLevel();
            } catch (NoSolutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLevelLoad() {
        levelCounter++;
        if(levelCounter==1){
            levelLabel = hudController.drawText("Level "+levelCounter,"font/PublicPixel-0W5Kv.ttf", Color.YELLOW,30,50,50,30,30);
        }
        else{
            levelLabel.setText("Level "+levelCounter);
        }
        hero.setLevel(levelAPI.getCurrentLevel());
    }

    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
