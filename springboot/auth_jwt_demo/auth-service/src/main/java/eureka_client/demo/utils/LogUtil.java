package eureka_client.demo.utils;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collection;

public class LogUtil {

    private static void print(String msg) {
        System.out.println(msg);
    }

    private static void print(String tag, String msg) {
        System.out.println(String.format("[%s] %s", tag, msg
        ));
    }

    private static void print(String tag, String format, Object... argArray) {
        System.out.println(String.format("[%s] ", tag) + String.format(format, argArray));
    }

    public static void d(String tag, String msg) {
        print(tag, msg);
    }

    public static void d(String tag, String format, Object... argArray) {
        print(tag, format, argArray);
    }

    public static void newLine() {
        System.out.println();
    }

    public static void e(String tag, Throwable e) {
        e(tag, e.getMessage());
    }

    public static void e(String tag, String errStr, Object... argArray) {
        d("[ ERROR ] " + tag, errStr, argArray);
    }

    public static void i(String tag, String msg) {
        print(tag, msg);
    }

    public static void i(String tag, String format, Object... argArray) {
        print(tag, format, argArray);
    }

    public static String arrayToString(Object[] fileList) {
        if (fileList == null || fileList.length == 0)
            return "[]";
        StringBuilder sb = new StringBuilder(String.format("{len=%s}[", fileList.length));
        for (Object obj : fileList) {
            sb.append(obj).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public  static String arrayToString(Collection<?> fileList) {
        if (fileList == null || fileList.size() == 0)
            return "[]";
        StringBuilder sb = new StringBuilder(String.format("{len=%s}[", fileList.size()));
        for (Object obj : fileList) {
            sb.append(obj).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
