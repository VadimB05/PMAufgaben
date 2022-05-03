import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArrayListTest {

    private ArrayList<String> list;

    @Before
    public void setUp(){
        list = new ArrayList<>();
        list.add("potato");
        list.add("carrot");
    }

    @Test
    public void testArrayList(){
        assertEquals(list.get(0),"potato");
        assertEquals(list.get(1),"carrot");
    }

    @Test
    public void testAdd(){
        list.add("weiteres Element");
        assertEquals(list.size(),3);
        assertEquals(list.get(0),"potato");
        assertEquals(list.get(1),"carrot");
        assertEquals(list.get(2),"weiteres Element");
    }

    @Test
    public void testRemoveObject(){
        list.remove(list.get(1));
        assertEquals(list.size(),1);
        assertEquals(list.get(0),"potato");
    }

    @Test
    public void testRemoveIndex(){
        list.remove(1);
        assertEquals(list.size(),1);
        assertEquals(list.get(0),"potato");
    }

    @After
    public void tearDown() throws Exception {
        list = null;
        assertNull(list);
    }

}
