// Ergänzen Sie UtilityTest so, dass alle Testmethoden grün werden.
// Die vorgegebenen Klassen dürfen nicht geändert werden.
// Die Testaufrufe müssen auch erhalten bleiben.
public class UtilityTest {
    private Utility utilityClass;
    // Initialisieren Sie die Attribute entsprechend vor jedem Test.

    @Test
    void test_nonEvilAdd() {
        Assertions.assertEquals(10, utilityClass.nonEvilAdd(9, 1));
    }

    @Test
    void test_evilAdd() {
        Assertions.assertEquals(10, utilityClass.evilAdd(9, 1));
    }

    @Test
    void test_veryEvilAdd() {
        utilityClass.veryEvilAdd(9, 1);
        Assertions.assertEquals(10, utilityClass.getIntResult());
    }
}
