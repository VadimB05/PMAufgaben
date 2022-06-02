package character.monster;

import graphic.Animation;
import java.util.ArrayList;
import java.util.List;
import static desktop.MyGame.stageCounter;

public class Variant {

    public enum Variants{
        IMP,
        CHORT
    }

    public final Variants type;
    public final String name;
    public final int health;
    public final int strength;
    public final int exp;
    public final float movementSpeed;
    public Animation runAnimationRight;
    public Animation runAnimationLeft;
    public Animation idleAnimationRight;
    public Animation idleAnimationLeft;

    public Variant(Variants type){
        this.type=type;

        List<String> idleAnimationRightList = new ArrayList<>();
        List<String> idleAnimationLeftList = new ArrayList<>();
        List<String> runAnimationRightList = new ArrayList<>();
        List<String> runAnimationLeftList = new ArrayList<>();
        switch(type){
            case IMP -> {
                this.name="Imp";
                this.movementSpeed=0.12f;
                this.health=(2* stageCounter)+10;
                this.strength=stageCounter;
                this.exp=(30+ stageCounter * stageCounter -(20+ stageCounter))+30;
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
            }
            case CHORT -> {
                this.name="Chort";
                this.movementSpeed=0.06f;
                this.health=(2* stageCounter)+10;
                this.strength=stageCounter;
                this.exp=(30+ stageCounter * stageCounter -(20+ stageCounter))+20;
                idleAnimationRightList.add("character/monster/chort_idle_anim_f0.png");
                idleAnimationRightList.add("character/monster/chort_idle_anim_f1.png");
                idleAnimationRightList.add("character/monster/chort_idle_anim_f2.png");
                idleAnimationRightList.add("character/monster/chort_idle_anim_f3.png");
                idleAnimationRight = new Animation(idleAnimationRightList,8);
                idleAnimationLeftList.add("character/monster/chort_idle_anim_mirrored_f0.png");
                idleAnimationLeftList.add("character/monster/chort_idle_anim_mirrored_f1.png");
                idleAnimationLeftList.add("character/monster/chort_idle_anim_mirrored_f2.png");
                idleAnimationLeftList.add("character/monster/chort_idle_anim_mirrored_f3.png");
                idleAnimationLeft = new Animation(idleAnimationLeftList, 8);
                runAnimationRightList.add("character/monster/chort_run_anim_f0.png");
                runAnimationRightList.add("character/monster/chort_run_anim_f1.png");
                runAnimationRightList.add("character/monster/chort_run_anim_f2.png");
                runAnimationRightList.add("character/monster/chort_run_anim_f3.png");
                runAnimationRight = new Animation(runAnimationRightList, 4);
                runAnimationLeftList.add("character/monster/chort_run_anim_mirrored_f0.png");
                runAnimationLeftList.add("character/monster/chort_run_anim_mirrored_f1.png");
                runAnimationLeftList.add("character/monster/chort_run_anim_mirrored_f2.png");
                runAnimationLeftList.add("character/monster/chort_run_anim_mirrored_f3.png");
                runAnimationLeft = new Animation(runAnimationLeftList, 4);
            }
            default -> {
                this.name="";
                this.movementSpeed=0;
                this.health=0;
                this.strength=0;
                this.exp=0;
            }
        }
    }
}
