package inventory;


import item.Items;
import logging.InventoryFormatter;
import logging.StandardFormatter;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventory {
    Logger logger;
    ConsoleHandler handlerInventory;
    ArrayList<Items> inventory;

    public Inventory() {
        logger = Logger.getLogger(this.getClass().getName());
        handlerInventory = new ConsoleHandler();

        for(Handler handler : logger.getHandlers()){
            logger.removeHandler(handler);
        }

        inventory = new ArrayList<>();

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
                    inventoryItems.append("6 zum fallen lassen)\n");
                }else if(i==1){
                    inventoryItems.append("7 zum fallen lassen)\n");
                }else if(i==2){
                    inventoryItems.append("8 zum fallen lassen)\n");
                }else{
                    inventoryItems.append("9 zum fallen lassen)\n");
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
