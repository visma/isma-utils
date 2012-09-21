package org.isma.utils;

import java.util.Comparator;
public interface Labeleable {
    String getLabel();


    public class LabeleableComparator implements Comparator<Labeleable> {

        public int compare(Labeleable o1, Labeleable o2) {
            return o1.getLabel().toLowerCase().compareTo(o2.getLabel().toLowerCase());
        }
    }
}
