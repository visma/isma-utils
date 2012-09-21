package org.isma.utils.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class CollectionHelperTest {

    @Test
    public void test_buildIndexedMap() throws Exception {
        List<Integer> integers = Arrays.asList(10, 10, 8, 6, 11, 6);
        Map<Integer,List<Integer>> indexedMap = CollectionHelper.buildIndexedMap(integers, new NumberComparator());
        assertEquals(4, indexedMap.size());

        assertEquals(2, indexedMap.get(0).size());
        assertEquals(6, indexedMap.get(0).get(0).intValue());
        assertEquals(6, indexedMap.get(0).get(1).intValue());

        assertEquals(1, indexedMap.get(1).size());
        assertEquals(8, indexedMap.get(1).get(0).intValue());

        assertEquals(2, indexedMap.get(2).size());
        assertEquals(10, indexedMap.get(2).get(0).intValue());
        assertEquals(10, indexedMap.get(2).get(1).intValue());

        assertEquals(1, indexedMap.get(3).size());
        assertEquals(11, indexedMap.get(3).get(0).intValue());
    }

    class NumberComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.intValue() - o2.intValue();
        }
    }
}
