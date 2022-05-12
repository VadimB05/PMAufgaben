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

    /*  1. Äquivalenzklassen
            |________________________________________________________________|
            | Eingabe       | gültige ÄK        | ungültige ÄK               |
            |________________________________________________________________|
            | alter         | gÄK1: [16,99]     | uÄK2: alter < 0            |
            |               |                   | uÄK3: -1 < alter < 16      |
            |               |                   | uÄK4: 99 < alter           |
            | motivation    | gÄK5: [4,7]       | uÄK6: motivation < 0       |
            |               |                   | uÄK7: -1 < motivation < 4  |
            |               |                   | uÄK8: 7 < motivation < 11  |
            |               |                   | uÄK9: 10 < motivation      |
            |_______________|___________________|____________________________|

        2. Grenzwertanalyse
        Alter:      -1(uÄK2o) und 0(uÄK3u) sowie 15(uÄK3o) und 16(gÄK1u) sowie 99(gÄK1o) und 100(uÄK3u)
        Motivation: -1(uÄK6o) und 0(uÄK7u) sowie 3(uÄK7o) und 4(gÄK5u) sowie 7(gÄK5o) und 8(uÄK8u) sowie 10(uÄK8o) und 11(uÄK9u)

        3. Testfälle
            |_______________________________________________________________________________________________________________________________________________________________________|
            | Testnummer            |   1           |   3           |   2           |   3           |   4           |   5           |   6           |   7           |   8           |
            | geprüfte ÄK           | gÄK1          |   gÄK5        | uÄK3          |   uÄK2        |   uÄK4        |    uÄK8       |   uÄK9        |   uÄK6        |   uÄK7        |
            | Alter                 |   20          |   20          |   15          |   -3          |   100         |   55          |   40          |   45          |   49          |
            | Motivation            |   4           |   4           |   6           |   5           |   7           |   8           |   11          |   -2          |   2           |
            | Erwartetes Ergebnis   |   true        |    true       |   false       |   Exception   |   Exception   |   false       |   Exception   |   Exception   |   false       |
            |_______________________________________________________________________________________________________________________________________________________________________|



            |_______________________________________________________________________________________________________________________|
            | Testnummer            |   1           |   2           |   3           |   4           |   5           |   6           |
            | geprüfte ÄK           | uÄK2o         |   uÄK3u       |   uÄK3o       |   ugÄK1u      |   gÄK1o       |   uÄK3u       |
            | Alter                 |   -1          |   0           |   15          |   16          |   99          |   100         |
            | Motivation            |   4           |   6           |   5           |   7           |   6           |   5           |
            | Erwartetes Ergebnis   |   Exception   |   false       |   false       |   true        |   true        |   Exception   |
            |_______________________________________________________________________________________________________________________|
            |___________________________________________________________________________________________________________________________________________________|
            | Testnummer            |   7           |   8           |   9           |   10          |   11          |   12          |   13          |   14
            | geprüfte ÄK           | uÄK6o         |   uÄK7u       |   uÄK7o       |   gÄk5u       |   gÄK5o       |   uÄK8u       |   uÄK8o       |   uÄK9u
            | Alter                 |   20          |   20          |   20          |   20          |   20          |   20          |   20          |   20
            | Motivation            |   -1          |   0           |   3           |   4           |   7           |   8           |   10          |   11
            | Erwartetes Ergebnis   |   Exception   |   false       |   false       |   true        |   true        |   false       |   false       |   Exception
            |___________________________________________________________________________________________________________________________________________________|

      */
}
