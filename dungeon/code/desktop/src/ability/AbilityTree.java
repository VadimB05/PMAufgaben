package ability;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class AbilityTree {
    Logger logger;
    ConsoleHandler handlerAbilityTree;
    ArrayList<Abilitys> abilityTreeList;

    /** Constructor. Sets loggers for outputting information and adds abilitys to the AbilityTree */
    public AbilityTree(){
        logger = Logger.getLogger(this.getClass().getName());
        handlerAbilityTree = new ConsoleHandler();
        abilityTreeList = new ArrayList<>();
        addAbilityToAbilityTree();


    }

    /** Adds Abilities to the AbilityTree on creation */
    public void addAbilityToAbilityTree(){
        abilityTreeList.add(new Blackhole());
        abilityTreeList.add(new Healability());
        abilityTreeList.add(new PowerUpability());
    }

    /** Show what is in the AbilityTree as a String. */
    public void showAbilityTree(){
        StringBuilder abilityTree = new StringBuilder();
        abilityTree.append("Fähigkeiten Baum: ");
        if(!abilityTreeList.isEmpty()){
            for(int i = 0; i< abilityTreeList.size(); i++){
                if(i >= 1){
                    abilityTree.append(",");
                }
                abilityTree.append(" ").append(abilityTreeList.get(i).
                    getName()).append(" (").append(i + 1).append(" zum Aktivieren und");
            }
            logger.info(abilityTree.toString());
        } else {
            logger.info("Fehler");
        }
    }
}
