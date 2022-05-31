import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SortTest {
    private static final Logger LOGGER = Logger.getLogger(SortTest.class.getSimpleName());

    /** Used to create new lists with the same student objects. */
    private static final List<Student> STUDENTS;

    private static List<Student> ascByAge;
    private static List<Student> descByAge;
    private static List<Student> ascByName;
    private static List<Student> descByName;
    private List<Student> students;

    static {
        STUDENTS = new ArrayList<>();
        try {
            STUDENTS.add(new Student("Max", "10.07.2000"));
            STUDENTS.add(new Student("Anna", "05.11.1993"));
            STUDENTS.add(new Student("Bob", "23.01.1999"));
            STUDENTS.add(new Student("Melanie", "01.05.2001"));
            STUDENTS.add(new Student("Peter", "17.01.1995"));
        } catch (ParseException e) {
            LOGGER.warning("Some or all entrys of sample list could not be created.");
        }
    }

    @BeforeClass
    public static void setUp() {
        ascByAge = new ArrayList<>(STUDENTS);
        ascByAge.sort(Comparator.comparing(Student::getBirthday));
        descByAge = new ArrayList<>(STUDENTS);
        descByAge.sort(Comparator.comparing(Student::getBirthday, Comparator.reverseOrder()));
        ascByName = new ArrayList<>(STUDENTS);
        ascByName.sort(Comparator.comparing(Student::getName));
        descByName = new ArrayList<>(STUDENTS);
        descByName.sort(Comparator.comparing(Student::getName, Comparator.reverseOrder()));
    }

    @Before
    public void resetList() {
        students = new ArrayList<>(STUDENTS);
    }

    @Test
    public void testSort_1a() {
        assertThat(StudentSort.sort_1a(students), is(ascByAge));
    }

    @Test
    public void testSort_1b() {
        assertThat(StudentSort.sort_1b(students), is(descByName));
    }

    @Test
    public void testSort_2a() {
        assertThat(StudentSort.sort_2a(students), is(ascByAge));
    }

    @Test
    public void testSort_2b() {
        assertThat(StudentSort.sort_2b(students), is(ascByAge));
    }

    @Test
    public void testSort_3a() {
        assertThat(StudentSort.sort_3a(students), is(ascByName));
    }

    @Test
    public void testSort_3b() {
        assertThat(StudentSort.sort_3b(students), is(ascByName));
    }

    @Test
    public void testSort_4a() {
        assertThat(StudentSort.sort_4a(students), is(descByAge));
    }

    @Test
    public void testSort_4b() {
        assertThat(StudentSort.sort_4b(students), is(ascByName));
    }
}
