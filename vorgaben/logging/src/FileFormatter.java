import java.util.logging.SimpleFormatter;


public class FileFormatter extends SimpleFormatter {
    private final String name;

    public FileFormatter(String n) {
        name = n;
    }
}
