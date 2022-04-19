import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class SecondFormatter extends SimpleFormatter{
    private final String name;
    public SecondFormatter(String n){
        name = n;
    }

    @Override
    public String format(LogRecord record) {
        return record.getLoggerName()+","+record.getLevel()+","+record.getSourceMethodName()+","+record.getSourceClassName()+","+record.getMessage()+"\n";
        //return record.getLoggerName()+";"+record.getLevel()+";"+record.getSourceMethodName()+";"+record.getSourceClassName()+";"+record.getMessage()+"\n";
    }
}
