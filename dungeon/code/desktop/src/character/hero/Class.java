package character.hero;

import graphic.Animation;

import java.util.ArrayList;
import java.util.List;

public class Class {

    public enum Classes{
        RANGER,
        MAGE,
        KNIGHT
    }

    public final Classes type;
    public final String name;
    public final int health;
    public final int strength;
    public final int mana;
    public final int defense;
    public final float movmentSpeed;
    public Animation runAnimationRight;
    public Animation runAnimationLeft;
    public Animation idleAnimationRight;
    public Animation idleAnimationLeft;

    public Class(Classes type){
        this.type=type;

        List<String> idleAnimationRightList = new ArrayList<>();
        List<String> idleAnimationLeftList = new ArrayList<>();
        List<String> runAnimationRightList = new ArrayList<>();
        List<String> runAnimationLeftList = new ArrayList<>();
        switch (type){
            case RANGER -> {
                this.name="Schütze";
                this.health=40;
                this.mana=15;
                this.strength=6;
                this.defense=5;
                this.movmentSpeed=0.35f;
                idleAnimationRightList.add("character/ranger/elf_f_idle_anim_f0.png");
                idleAnimationRightList.add("character/ranger/elf_f_idle_anim_f1.png");
                idleAnimationRightList.add("character/ranger/elf_f_idle_anim_f2.png");
                idleAnimationRightList.add("character/ranger/elf_f_idle_anim_f3.png");
                idleAnimationRight = new Animation(idleAnimationRightList, 8);
                idleAnimationLeftList.add("character/ranger/elf_f_idle_anim_f0_m.png");
                idleAnimationLeftList.add("character/ranger/elf_f_idle_anim_f1_m.png");
                idleAnimationLeftList.add("character/ranger/elf_f_idle_anim_f2_m.png");
                idleAnimationLeftList.add("character/ranger/elf_f_idle_anim_f3_m.png");
                idleAnimationLeft = new Animation(idleAnimationLeftList,8);
                runAnimationRightList.add("character/ranger/elf_f_run_anim_f0.png");
                runAnimationRightList.add("character/ranger/elf_f_run_anim_f1.png");
                runAnimationRightList.add("character/ranger/elf_f_run_anim_f2.png");
                runAnimationRightList.add("character/ranger/elf_f_run_anim_f3.png");
                runAnimationRight = new Animation(runAnimationRightList,8);
                runAnimationLeftList.add("character/ranger/elf_f_run_anim_f0_m.png");
                runAnimationLeftList.add("character/ranger/elf_f_run_anim_f1_m.png");
                runAnimationLeftList.add("character/ranger/elf_f_run_anim_f2_m.png");
                runAnimationLeftList.add("character/ranger/elf_f_run_anim_f3_m.png");
                runAnimationLeft = new Animation(runAnimationLeftList, 8);
            }
            case MAGE -> {
                this.name="Magier";
                this.health=40;
                this.mana=20;
                this.strength=6;
                this.defense=5;
                this.movmentSpeed=0.2f;
                idleAnimationRightList.add("character/mage/wizard_m_idle_anim_f0.png");
                idleAnimationRightList.add("character/mage/wizard_m_idle_anim_f1.png");
                idleAnimationRightList.add("character/mage/wizard_m_idle_anim_f2.png");
                idleAnimationRightList.add("character/mage/wizard_m_idle_anim_f3.png");
                idleAnimationRight = new Animation(idleAnimationRightList, 8);
                idleAnimationLeftList.add("character/mage/wizard_m_idle_anim_f0_m.png");
                idleAnimationLeftList.add("character/mage/wizard_m_idle_anim_f1_m.png");
                idleAnimationLeftList.add("character/mage/wizard_m_idle_anim_f2_m.png");
                idleAnimationLeftList.add("character/mage/wizard_m_idle_anim_f3_m.png");
                idleAnimationLeft = new Animation(idleAnimationLeftList,8);
                runAnimationRightList.add("character/mage/wizard_m_run_anim_f0.png");
                runAnimationRightList.add("character/mage/wizard_m_run_anim_f1.png");
                runAnimationRightList.add("character/mage/wizard_m_run_anim_f2.png");
                runAnimationRightList.add("character/mage/wizard_m_run_anim_f3.png");
                runAnimationRight = new Animation(runAnimationRightList,8);
                runAnimationLeftList.add("character/mage/wizard_m_run_anim_f0_m.png");
                runAnimationLeftList.add("character/mage/wizard_m_run_anim_f1_m.png");
                runAnimationLeftList.add("character/mage/wizard_m_run_anim_f2_m.png");
                runAnimationLeftList.add("character/mage/wizard_m_run_anim_f3_m.png");
                runAnimationLeft = new Animation(runAnimationLeftList, 8);
            }
            case KNIGHT -> {
                this.name="Ritter";
                this.health=40;
                this.mana=10;
                this.strength=8;
                this.defense=10;
                this.movmentSpeed=0.2f;
                idleAnimationRightList.add("character/knight/knight_m_idle_anim_f0.png");
                idleAnimationRightList.add("character/knight/knight_m_idle_anim_f1.png");
                idleAnimationRightList.add("character/knight/knight_m_idle_anim_f2.png");
                idleAnimationRightList.add("character/knight/knight_m_idle_anim_f3.png");
                idleAnimationRight = new Animation(idleAnimationRightList, 8);
                idleAnimationLeftList.add("character/knight/knight_m_idle_anim_mirrored_f0.png");
                idleAnimationLeftList.add("character/knight/knight_m_idle_anim_mirrored_f1.png");
                idleAnimationLeftList.add("character/knight/knight_m_idle_anim_mirrored_f2.png");
                idleAnimationLeftList.add("character/knight/knight_m_idle_anim_mirrored_f3.png");
                idleAnimationLeft = new Animation(idleAnimationLeftList,8);
                runAnimationRightList.add("character/knight/knight_m_run_anim_f0.png");
                runAnimationRightList.add("character/knight/knight_m_run_anim_f1.png");
                runAnimationRightList.add("character/knight/knight_m_run_anim_f2.png");
                runAnimationRightList.add("character/knight/knight_m_run_anim_f3.png");
                runAnimationRight = new Animation(runAnimationRightList,8);
                runAnimationLeftList.add("character/knight/knight_m_run_anim_mirrored_f0.png");
                runAnimationLeftList.add("character/knight/knight_m_run_anim_mirrored_f1.png");
                runAnimationLeftList.add("character/knight/knight_m_run_anim_mirrored_f2.png");
                runAnimationLeftList.add("character/knight/knight_m_run_anim_mirrored_f3.png");
                runAnimationLeft = new Animation(runAnimationLeftList, 8);
            }
            default -> {
                this.name="";
                this.health=0;
                this.mana=0;
                this.strength=0;
                this.defense=0;
                this.movmentSpeed=0;
            }
        }
    }
}
