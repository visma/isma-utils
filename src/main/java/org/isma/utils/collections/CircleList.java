package org.isma.utils.collections;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CircleList<E> extends ArrayList<E> {
    public CircleList() {
        super();
    }

    public CircleList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            return super.add(e);
        }
        return false;
    }


    //TODO TU
    public List<E> nextList(E element) {
        List<E> nextList = new ArrayList<E>();
        E next = next(element);
        while (next != element && next != null){
            nextList.add(next);
            next = next(next);
        }
        return nextList;
    }
    public E next(E element) {
        int indexOf = indexOf(element);
        if (indexOf == -1){
            return null;
        }
        if (indexOf + 1 == size()) {
            return get(0);
        }
        return get(indexOf + 1);
    }

    public E next(E element, Predicate predicate) {
        boolean respectPredicate = predicate.evaluate(element);
        int matches = CollectionUtils.countMatches(this, predicate);
        if (respectPredicate && matches == 1) {
            return null;
        }
        E nextElement = next(element);
        while (!predicate.evaluate(nextElement)) {
            nextElement = next(nextElement);
            if (nextElement== element){
                return null;
            }
        }
        return nextElement;
    }
}
