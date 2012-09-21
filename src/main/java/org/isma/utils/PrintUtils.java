package org.isma.utils;

public class PrintUtils {
    private PrintUtils() {
    }


    public static String getString(int depth, String str) {
        for (int i = 0; i < depth; i++) {
            str = "\t" + str;
        }
        return str;
    }
}
