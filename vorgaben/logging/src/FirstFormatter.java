import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class FirstFormatter extends SimpleFormatter {
    private final String name;
    public FirstFormatter(String n){
        name = n;
    }

    @Override
    public String format(LogRecord record) {
        return "------------\n"+"Logger: "+record.getLoggerName()+"\n"+"Level: "+record.getLevel()+"\n"+"Class: "+record.getSourceMethodName()+"\n"+"Method: "+record.getSourceClassName()+"\n"+"Message: "+record.getMessage()+"\n------------\n";
    }
}
