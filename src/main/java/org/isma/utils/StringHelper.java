package org.isma.utils;

import java.util.List;

public class StringHelper {
    public static <E> String join(List<E> list, String separator, JoinValueExtractor<E> joinValueExtractor) {
        StringBuilder sb = new StringBuilder();
        boolean firstElement = true;
        for (E e : list) {
            if (firstElement) {
                sb.append(joinValueExtractor.joinValue(e));
                firstElement = false;
            } else {
                sb.append(separator + joinValueExtractor.joinValue(e));
            }
        }
        return sb.toString();
    }

    public interface JoinValueExtractor<E> {
        public String joinValue(E element);
    }
}
