package logging;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class EquipmentFormatter extends SimpleFormatter {
    private final String name;

    public EquipmentFormatter(String name) {
        this.name = name;
    }

    @Override
    public String format(LogRecord record) {
        return record.getMessage()+"\n";
    }
}
