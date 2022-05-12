import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialArrayListTest {

    private SpecialArrayList specialArrayList;

    @Before
    public void setUp() {
        specialArrayList = new SpecialArrayList();
    }

    @Test
    public void testFirstTask(){
        specialArrayList.concatAddStrings("","");
        assertEquals(specialArrayList.get(0),"");
    }

    @Test
    public void testSecondTask(){
        specialArrayList.concatAddStrings("","a");
        assertEquals(specialArrayList.get(0),"a");
    }

    @Test
    public void testThirdTask(){
        specialArrayList.concatAddStrings("a","");
        assertEquals(specialArrayList.get(0),"a");
    }

    @Test
    public void testFourthTask(){
        specialArrayList.concatAddStrings("abc","123");
        assertEquals(specialArrayList.get(0),"abc123");
    }

    @After
    public void tearDown(){
        specialArrayList = null;
    }

}
