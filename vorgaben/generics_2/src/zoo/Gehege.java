package zoo;

import zoo.tiere.Tier;
import zoo.tiere.fische.Fisch;
import zoo.tiere.fische.Salzwasserfisch;
import zoo.tiere.saeuger.katzen.Katze;
import zoo.tiere.saeuger.katzen.Loewe;

public class Gehege<Tier> {
    private final String name;

    public Gehege(String name) {
        this.name = name;
    }

    void aufnehmen(Tier tier){

    }

    void entfernen(Tier tier){

    }

    String getName(){
        return name;
    }

    public static void main(String[] args) {
        Zoo<Gehege> zoo = new Zoo<>();

        Gehege<Fisch> aquarium = new Gehege<>("Aquarium");
        Gehege<Katze> loewenGehege = new Gehege<>("Loewen Gehege");


        Salzwasserfisch clownfisch = new Salzwasserfisch("Clownfisch");
        Salzwasserfisch palettenDoktorfisch = new Salzwasserfisch("Paletten Doktorfisch");

        Loewe loeweManfred = new Loewe("Manfred");
        Loewe loeweJoachim = new Loewe("Joachim");


        aquarium.aufnehmen(clownfisch);
        aquarium.aufnehmen(palettenDoktorfisch);

        loewenGehege.aufnehmen(loeweManfred);
        loewenGehege.aufnehmen(loeweJoachim);

        zoo.errichten(aquarium);
        zoo.errichten(loewenGehege);

    }
}
