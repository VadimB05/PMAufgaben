package bundesliga.generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasketballSpielerTest {
    private BasketballSpieler s;

    @Before
    public void setUp() {
        s = new BasketballSpieler("Klaus Klausedahl");
    }

    /** Laesst sich ein BasketballSpieler anlegen? */
    @Test
    public void testBasketballSpieler() {
        assertNotNull(s);
    }

    /** Funktioniert die Methode <code>BasketballSpieler#getName()</code>? */
    @Test
    public void testGetName() {
        assertEquals(s.getName(), "Klaus Klausedahl");
    }
}
