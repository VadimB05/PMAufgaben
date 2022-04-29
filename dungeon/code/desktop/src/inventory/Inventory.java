package inventory;


import controller.MainController;
import hud.Icon;
import item.Items;
import item.potion.Potion;
import logging.InventoryFormatter;
import tools.Point;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventory {
    Logger logger = Logger.getLogger(Inventory.class.getName());
    ConsoleHandler handlerInventory = new ConsoleHandler();
    ArrayList<Items> inventory = new ArrayList<>();

    public Inventory() {
        handlerInventory.setLevel(Level.INFO);
        handlerInventory.setFormatter(new InventoryFormatter("Inventory Logger"));
        logger.setLevel(Level.INFO);
        logger.addHandler(handlerInventory);
        logger.setUseParentHandlers(false);
    }

    /** add items to our inventory and set our inventory to a maximum size of 4
     *
     * @param items, which we are adding to the inventory ArrayList
     * */
    public boolean addToInventory(Items items){
        if(inventory.size()<4){
            inventory.add(items);
            logger.info(items.getName() + " wurde zum Inventar hinzugefuegt.");
            return true;
        }else {
            logger.info("Das Inventar ist voll!");
        }
        return false;
    }

    /** Show what is in our inventory and which button we have to hit, to use or drop the item*/
    public void getInventoryItems(){
        StringBuilder inventoryItems = new StringBuilder();
        inventoryItems.append("Das Inventar beinhaltet: ");
        if(!inventory.isEmpty()){
            for (int i=0;i<inventory.size();i++) {
                if(i>=1){
                    inventoryItems.append(",");
                }
                inventoryItems.append(" ").append(inventory.get(i).getName()).append(" (").append(i+1).append(" zum Aktivieren und ");
                if(i==0){
                    inventoryItems.append("6 zum fallen lassen)");
                }else if(i==1){
                    inventoryItems.append("7 zum fallen lassen)\n");
                }else if(i==2){
                    inventoryItems.append("8 zum fallen lassen)");
                }else{
                    inventoryItems.append("9 zum fallen lassen)");
                }
            }
            logger.info(inventoryItems.toString());
        }else {
            logger.info("Das Inventar ist leer.");
        }
    }

    /** return the inventory ArrayList */
    public ArrayList<Items> getInventoryArrayList(){
        return inventory;
    }

    /** remove an item from the inventory ArrayList
     *
     * @param position , position to remove the item from the inventory ArrayList
     * */
    public void dropItemInventory(int position){
        inventory.remove(position);
    }

}
