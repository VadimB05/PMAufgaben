package bundesliga.generic;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IMannschaftTest {

    /**
     * Sind die richtigen Methoden im Interface vorhanden? Korrekte Einschränkung der Typvariablen?
     */
    @Test
    public void test() {
        class Wuppie implements ISpieler {

            @Override
            public String getName() {
                return null;
            }
        }

        IMannschaft<Wuppie> m =
                new IMannschaft<Wuppie>() {

                    @Override
                    public boolean aufnehmen(Wuppie spieler) {
                        return false;
                    }

                    @Override
                    public boolean rauswerfen(Wuppie spieler) {
                        return false;
                    }
                };

        assertNotNull(m);
    }
}
