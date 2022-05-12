package inventory;

import item.Items;
import item.armor.ChestPlate;
import item.armor.Shield;
import item.weapon.Weapons;
import logging.InventoryFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Equipment {
    Logger logger;
    ConsoleHandler handlerEquipment;
    ChestPlate chestPlate = null;
    Shield shield = null;
    Weapons weapons = null;
    private int defense, strength ;

    public Equipment() {
        logger = Logger.getLogger(Equipment.class.getName());

        handlerEquipment = new ConsoleHandler();

        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }

        handlerEquipment.setLevel(Level.INFO);
        handlerEquipment.setFormatter(new InventoryFormatter("Inventory Logger"));
        logger.setLevel(Level.INFO);
        logger.addHandler(handlerEquipment);
        logger.setUseParentHandlers(false);
    }

    /** Picks up an equipment and sets picked up to true
     *
     * @param items , which we are putting in our equipment slot
     * @return tmpitems/null , the equipment we had equipped
     * */
    public Items equipmentChange(Items items){
        logger.info(items.getName()+" ausgeruestet.");
        items.setPickedUp(true);
        if(items.getClass().equals(ChestPlate.class)){
            if(this.chestPlate!=null){
                ChestPlate tmpChestPlate = this.chestPlate;
                this.chestPlate = (ChestPlate) items;
                addDefenseChestPlate(this.chestPlate);
                return tmpChestPlate;
            }else {
                this.chestPlate = (ChestPlate) items;
                addDefenseChestPlate(this.chestPlate);
                return null;
            }
        }
        else if(items.getClass().equals(Shield.class)){
            if(this.shield!=null){
                Shield tmpShield = this.shield;
                this.shield = (Shield) items;
                addDefenseShield(this.shield);
                return tmpShield;
            }else {
                this.shield = (Shield) items;
                addDefenseShield(this.shield);
                return null;
            }
        }
        else {
            if(this.weapons!=null){
                Weapons tmpWeapon = this.weapons;
                this.weapons = (Weapons) items;
                addStrength(this.weapons);
                return tmpWeapon;
            }else {
                this.weapons = (Weapons) items;
                addStrength(this.weapons);
                return null;
            }
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
