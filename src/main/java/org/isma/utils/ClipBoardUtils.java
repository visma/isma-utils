package org.isma.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
public class ClipBoardUtils {
    private ClipBoardUtils() {
    }


    public static void copyToClipboard(String value){
        StringSelection stringSelection = new StringSelection(value);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
    }
}
