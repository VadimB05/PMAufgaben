import java.util.logging.SimpleFormatter;
import java.util.logging.LogRecord;

public class ConsoleFormatter extends SimpleFormatter {
    private final String name;

    public ConsoleFormatter(String n) {
        name = n;
    }

    @Override
    public String format(LogRecord record) {
        return "------------" + "\n" +
            "Logger: " + record.getLoggerName() + "\n" +
            "Level: " + record.getLevel() + "\n" +
            "Class: " + record.getSourceClassName() + "\n" +
            "Method: " + record.getSourceMethodName() + "\n" +
            "Message: " + record.getMessage() + "\n" +
            "------------";
    }
}
