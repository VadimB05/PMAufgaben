import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingbufferTest {
    Ringbuffer buffer;

    @BeforeEach
    void setUp() {
        buffer = new Ringbuffer(2);
    }

    @Test
    void addSuccessFirstElement() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertEquals(1, buffer.elementsCount());
    }

    @Test
    void addSuccessLastElement() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertDoesNotThrow(() -> buffer.add("2"));
        assertEquals(2, buffer.elementsCount());
    }

    @Test
    void addOverLimit() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertDoesNotThrow(() -> buffer.add("2"));
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> buffer.add(""));
        assertEquals("The Current Buffer is already full.", thrown.getMessage());
    }

    @Test
    void remove() {
        String element = "1";
        assertDoesNotThrow(() -> buffer.add(element));
        assertEquals(element, buffer.remove());
        assertEquals(0, buffer.elementsCount());
    }

    @Test
    void removeEmpty() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> buffer.remove());
        assertEquals("The Current Buffer does not contain any element.", thrown.getMessage());
    }

    @Test
    void checkRingAdd() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertDoesNotThrow(() -> buffer.add("2"));
        assertDoesNotThrow(() -> buffer.remove());
        assertDoesNotThrow(() -> buffer.remove());
        assertDoesNotThrow(() -> buffer.add("3"));
        assertEquals(1, buffer.elementsCount());
    }

    @Test
    void checkRingRemove() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertDoesNotThrow(() -> buffer.add("2"));
        assertDoesNotThrow(() -> buffer.remove());
        assertDoesNotThrow(() -> buffer.remove());
        assertDoesNotThrow(() -> buffer.add("3"));
        assertDoesNotThrow(() -> buffer.remove());
        assertEquals(0, buffer.elementsCount());
    }
}