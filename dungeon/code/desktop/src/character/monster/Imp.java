package character.monster;

import character.Character;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;

public class Imp extends Character {
    public Imp(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        idleAnimationRightList.add("character/monster/imp_idle_anim_f0.png");
        idleAnimationRightList.add("character/monster/imp_idle_anim_f1.png");
        idleAnimationRightList.add("character/monster/imp_idle_anim_f2.png");
        idleAnimationRightList.add("character/monster/imp_idle_anim_f3.png");
        idleAnimationRight = new Animation(idleAnimationRightList,8);
        idleAnimationLeftList.add("character/monster/imp_idle_anim_mirrored_f0.png");
        idleAnimationLeftList.add("character/monster/imp_idle_anim_mirrored_f1.png");
        idleAnimationLeftList.add("character/monster/imp_idle_anim_mirrored_f2.png");
        idleAnimationLeftList.add("character/monster/imp_idle_anim_mirrored_f3.png");
        idleAnimationLeft = new Animation(idleAnimationLeftList, 8);
        runAnimationRightList.add("character/monster/imp_run_anim_f0.png");
        runAnimationRightList.add("character/monster/imp_run_anim_f1.png");
        runAnimationRightList.add("character/monster/imp_run_anim_f2.png");
        runAnimationRightList.add("character/monster/imp_run_anim_f3.png");
        runAnimationRight = new Animation(runAnimationRightList, 4);
        runAnimationLeftList.add("character/monster/imp_run_anim_mirrored_f0.png");
        runAnimationLeftList.add("character/monster/imp_run_anim_mirrored_f1.png");
        runAnimationLeftList.add("character/monster/imp_run_anim_mirrored_f2.png");
        runAnimationLeftList.add("character/monster/imp_run_anim_mirrored_f3.png");
        runAnimationLeft = new Animation(runAnimationLeftList, 4);
        setMovementSpeed(0.12f);
    }
}
