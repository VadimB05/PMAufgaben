package logging;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class InventoryFormatter extends SimpleFormatter {
    private final String name;

    /** Constructor for the formatter */
    public InventoryFormatter(String name) {
        this.name = name;
    }

    @Override
    public String format(LogRecord record) {
        return record.getMessage()+"\n";
    }
}
