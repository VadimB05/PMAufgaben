package inventory;


import controller.MainController;
import hud.Icon;
import item.Items;
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

    public void getInventoryItems(){
        StringBuilder inventoryItems = new StringBuilder();
        inventoryItems.append("Das Inventar beinhaltet: ");
        if(!inventory.isEmpty()){
            for (int i=0;i<inventory.size();i++) {
                if(i>=1){
                    inventoryItems.append(",");
                }
                inventoryItems.append(" ").append(inventory.get(i).getName());
            }
            logger.info(inventoryItems.toString());
        }else {
            logger.info("Das Inventar ist leer.");
        }
    }

    public ArrayList<Items> getInventoryArrayList(){
        return inventory;
    }

}
