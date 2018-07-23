package util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionBuilder {


    public static String getExceptionMessage(Exception ex, Integer messageSize) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString().substring(0, messageSize);
    }

    public static String getExceptionMessage(Exception ex) {
        return getExceptionMessage(ex, 300);
    }


}
