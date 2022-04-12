package bundesliga.generic;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ISpielerTest {

    /**
     * Sind die richtigen Methoden im Interface vorhanden? Absicherung gegen versehentliches Ändern
     * der Vorgaben ...
     */
    @Test
    public void test() {
        ISpieler s =
                new ISpieler() {

                    @Override
                    public String getName() {
                        return null;
                    }
                };

        assertNotNull(s);
    }
}
