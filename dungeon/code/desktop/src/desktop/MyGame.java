package desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import controller.MainController;
import hud.Icon;
import inventory.Inventory;
import item.Items;
import item.weapon.Sword;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import tools.Point;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MyGame extends MainController {
    private Label levelLabel;
    private MyHero hero;
    private Sword sword;
    private int levelCounter = 0;
    Inventory inventory = new Inventory();



    @Override
    protected void setup() {
        levelAPI.setGenerator(new LevelLoader());
        hero = new MyHero(painter,batch);
        sword = new Sword(painter,batch,"item/weapon_knight_sword.png", "Schwert");
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
        entityController.add(sword);
        hudController.add(new Icon(hudPainter,hudBatch,new Point(10f,10f),"hud/ui_heart_full.png"));
        hudController.add(new Icon(hudPainter,hudBatch,new Point(170f,400f),"hud/inventar.png"));
    }

    @Override
    protected void beginFrame() {}

    @Override
    protected void endFrame() {
        int counterHUDInventory=1;
        if(levelAPI.getCurrentLevel().isOnEndTile(hero)){
            try {
                levelAPI.loadLevel();
            } catch (NoSolutionException e) {
                e.printStackTrace();
            }
        }
        if (hero.getPosition().x-sword.getPosition().x < 1 && hero.getPosition().y-sword.getPosition().y < 1){
            if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                if(inventory.addToInventory(sword)){
                    entityController.remove(sword);
                }
                for(Items items : inventory.getInventoryArrayList()){
                    hudController.add(new Icon(hudPainter,hudBatch,new Point(128+(counterHUDInventory*69),405f),items.getTexturePath()));
                    counterHUDInventory++;
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.I)){
            inventory.getInventoryItems();
        }
    }

    @Override
    public void onLevelLoad() {
        levelCounter++;
        if(levelCounter==1){
            levelLabel = hudController.drawText("Level "+levelCounter,"font/PublicPixel-0W5Kv.ttf", Color.YELLOW,14,50,50,20,5);
        }
        else{
            levelLabel.setText("Level "+levelCounter);
        }
        hero.setLevel(levelAPI.getCurrentLevel());
        sword.setLevel(levelAPI.getCurrentLevel());
    }

    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());

    }
}
