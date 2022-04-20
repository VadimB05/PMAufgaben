package rsvflottespeiche;

public class MitgliederVerwaltung {

    /**
     * Testet, ob ein Mitglied in den Verein aufgenommen werden kann.
     *
     * @param alter       Alter in Lebensjahren, Bereich [0, 99]
     * @param motivation  Motivation auf einer Scala von 0 bis 10
     * @return <code>true</code>, wenn das Mitglied aufgenommen werden kann, sonst <code>false</code>
     */
    public boolean testBeitritt(int alter, int motivation) {
        if (alter < 0 || alter > 99 || motivation < 0 || motivation > 10) {
            throw new IllegalArgumentException("Das Alter und die Motivation müssen innerhalb der Grenzen sein.");
        }
        if (alter < 16) {
            return false;
        }
        return motivation >= 4 && motivation <= 7;
    }
}
