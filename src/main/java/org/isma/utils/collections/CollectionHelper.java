package org.isma.utils.collections;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import java.util.*;

public class CollectionHelper {
    public static <I, O> void transformBiDimensionalList(List<List<I>> inputListList, final Transformer transformer) {
        CollectionUtils.transform(inputListList, new Transformer() {
            @Override
            public Object transform(Object inputList) {
                List<I> castedList = (List<I>) inputList;
                CollectionUtils.transform(castedList, transformer);
                return castedList;
            }
        });
    }

    public static <E> Map<Integer, List<E>> buildIndexedMap(Collection<E> collection, Comparator<E> comparator) {
        Map<Integer, List<E>> indexedMap = new HashMap<Integer, List<E>>();
        if (collection.isEmpty()) {
            return indexedMap;
        }
        List<E> clone = new ArrayList<E>(collection);
        Collections.sort(clone, comparator);

        int index = 0;
        E previousElement = clone.remove(0);
        indexedMap.put(index, new ArrayList<E>());
        indexedMap.get(index).add(previousElement);
        while (!clone.isEmpty()) {
            E element = clone.remove(0);
            if (comparator.compare(previousElement, element) != 0) {
                index++;
                indexedMap.put(index, new ArrayList<E>());
            }
            indexedMap.get(index).add(element);
            previousElement = element;
        }
        return indexedMap;
    }
}
