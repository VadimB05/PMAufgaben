package bundesliga.generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FussballSpielerTest {
    private FussballSpieler s;

    @Before
    public void setUp() {
        s = new FussballSpieler("Klaus Klausedahl");
    }

    /** Laesst sich ein FussballSpieler anlegen? */
    @Test
    public void testFussballSpieler() {
        assertNotNull(s);
    }

    /** Funktioniert die Methode <code>FussballSpieler#getName()</code>? */
    @Test
    public void testGetName() {
        assertEquals(s.getName(), "Klaus Klausedahl");
    }
}
