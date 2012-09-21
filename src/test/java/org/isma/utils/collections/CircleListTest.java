package org.isma.utils.collections;

import junit.framework.Assert;
import org.apache.commons.collections.Predicate;
import org.junit.Test;

public class CircleListTest {
    @Test
    public void test_next() throws Exception {
        CircleList<Integer> list = new CircleList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertEquals(2, list.next(1).intValue());
        Assert.assertEquals(3, list.next(2).intValue());
        Assert.assertEquals(1, list.next(3).intValue());
        Assert.assertEquals(null, list.next(4));
    }

    @Test
    public void test_next_predicate() throws Exception {
        //Nombre pair
        Predicate isEvenPredicate = new Predicate() {

            @Override
            public boolean evaluate(Object o) {
                return ((Integer) o).intValue() % 2 == 0;
            }
        };
        CircleList<Integer> list = new CircleList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Assert.assertEquals(2, list.next(1, isEvenPredicate).intValue());
        Assert.assertEquals(4, list.next(2, isEvenPredicate).intValue());
        Assert.assertEquals(4, list.next(3, isEvenPredicate).intValue());
        Assert.assertEquals(2, list.next(4, isEvenPredicate).intValue());
    }
}
