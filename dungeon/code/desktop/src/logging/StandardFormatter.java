package logging;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class StandardFormatter extends SimpleFormatter {
    private final String name;

    public StandardFormatter(String name) {
        this.name = name;
    }

    @Override
    public String format(LogRecord record) {
        return super.format(record);
    }
}
