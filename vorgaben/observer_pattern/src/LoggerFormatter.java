import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class LoggerFormatter extends SimpleFormatter {
    private final String name;

    public LoggerFormatter(String name){
        this.name = name;
    }

    @Override
    public String format(LogRecord record) {
        return name+": "+record.getMessage()+"\n";
    }
}
