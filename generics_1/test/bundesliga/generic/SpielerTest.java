package bundesliga.generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpielerTest {
    private ISpieler s;

    @Before
    public void setUp() {
        s = new Spieler("Werner");
    }

    /** Laesst sich ein Spieler anlegen? */
    @Test
    public void testSpieler() {
        assertEquals(s.getName(), "Werner");
    }

    /** Funktioniert die Methode <code>Spieler#getName()</code>? */
    @Test
    public void testGetName() {
        assertEquals(s.getName(), "Werner");
    }
}
