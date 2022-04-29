package inventory;

import item.Items;
import item.armor.Armor;
import item.armor.ChestPlate;
import item.armor.Shield;
import item.weapon.Weapons;
import logging.EquipmentFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Equipment {
    Logger logger = Logger.getLogger(Equipment.class.getName());
    ConsoleHandler handlerEquipment = new ConsoleHandler();
    ChestPlate chestPlate = null;
    Shield shield = null;
    Weapons weapons = null;
    private int defense, strength ;

    public Equipment() {
        handlerEquipment.setLevel(Level.INFO);
        handlerEquipment.setFormatter(new EquipmentFormatter("Inventory Logger"));
        logger.setLevel(Level.INFO);
        logger.addHandler(handlerEquipment);
        logger.setUseParentHandlers(false);
    }

    /** Picks up weapon and sets picked up to true
     *
     * @param weapons , which we are putting in our weapon slot
     * @return tmpweapons/null , the weapon we had equipped
     * */
    public Items equipWeapon(Weapons weapons){
        logger.info(weapons.getName()+" ausgeruestet.");
        weapons.setPickedUp(true);
        if(this.weapons!=null){
            Weapons tmpWeapon = this.weapons;
            this.weapons = weapons;
            addStrength(this.weapons);
            return tmpWeapon;
        }else {
            this.weapons = weapons;
            addStrength(this.weapons);
            return null;
        }
    }

    /** Picks up chestplate and sets picked up to true
     *
     * @param chestPlate , which we are putting in our chestplate slot
     * @return tmpChestPlate/null , the chestPlate we had equipped
     * */
    public Items equipChestPlate(ChestPlate chestPlate){
        logger.info(chestPlate.getName()+" ausgeruestet.");
        chestPlate.setPickedUp(true);
        if(this.chestPlate!=null){
            ChestPlate tmpChestPlate = this.chestPlate;
            this.chestPlate = chestPlate;
            addDefenseChestPlate(this.chestPlate);
            return tmpChestPlate;
        }else {
            this.chestPlate = chestPlate;
            addDefenseChestPlate(this.chestPlate);
            return null;
        }
    }

    /** Picks up shield and sets picked up to true
     *
     * @param shield , which we are putting in our shield slot
     * @return tmpshield/null , the shield we had equipped
     * */
    public Items equipShield(Shield shield){
        logger.info(shield.getName()+" ausgeruestet.");
        shield.setPickedUp(true);
        if(this.shield!=null){
            Shield tmpShield = this.shield;
            this.shield = shield;
            addDefenseShield(this.shield);
            return tmpShield;
        }else {
            this.shield = shield;
            addDefenseShield(this.shield);
            return null;
        }
    }

    /** Log all the items we have equipped */
    public void getEquimentItems(){
        if(weapons!=null){
            logger.info("Waffe: " + weapons.getName());
        }
        if(shield!=null){
            logger.info("Schild: " + shield.getName());
        }
        if(chestPlate!=null){
            logger.info("Ruestung: " + chestPlate.getName());
        }
    }

    /** Calculate the defense we have with the newly equipped chestPlate
     *
     * @param chestPlate , the chestPlate we just equipped
     * */
    public void addDefenseChestPlate(ChestPlate chestPlate){
        defense = 0;
        defense += chestPlate.getDefense();
        if(shield!=null){
            defense += shield.getDefense();
        }
    }

    /** Calculate the defense we have with the newly equipped shield
     *
     * @param shield , the shield we just equipped
     * */
    public void addDefenseShield(Shield shield){
        defense = 0;
        defense += shield.getDefense();
        if(chestPlate!=null){
            defense += chestPlate.getDefense();
        }
    }

    /** our all equipped defense
     *
     * @return defense
     * */
    public int getDefense() {
        return defense;
    }

    /** Calculate the strength we have with the newly equipped weapon
     *
     * @param weapons , the weapon we just equipped
     * */
    public void addStrength(Weapons weapons){
        strength = 0;
        strength += weapons.getDamage();
    }

}
