/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.collections;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.mysema.util.MultiIterator;

/**
 * MultiIteratorTest provides
 *
 * @author tiwe
 * @version $Id$
 */
@SuppressWarnings("unchecked")
public class MultiIteratorTest extends AbstractIteratorTest {

    private MultiIterator it;

    private List<Integer> list1 = Arrays.asList(1, 2);

    private List<Integer> list2 = Collections.emptyList();

    private List<Integer> list3, list4;

    @Test
    public void testEmptyList() {
        it = new MultiIterator(Arrays.asList(list1, list2));
        while (it.hasNext()) {
            it.next();
            fail("should return false on hasNext()");
        }
    }

    @Test
    public void testOneLevel() {
        it = new MultiIterator(Arrays.asList(list1));
        assertIteratorEquals(Arrays.asList(row(1), row(2)).iterator(), it);
    }

    @Test
    public void testTwoLevels() {
        list2 = Arrays.asList(10, 20, 30);
        it = new MultiIterator(Arrays.asList(list1, list2));
        Iterator<Object[]> base = Arrays.asList(row(1, 10), row(1, 20),
                row(1, 30), row(2, 10), row(2, 20), row(2, 30)).iterator();
        assertIteratorEquals(base, it);
    }

    @Test
    public void testThreeLevels() {
        list1 = Arrays.asList(1, 2);
        list2 = Arrays.asList(10, 20, 30);
        list3 = Arrays.asList(100, 200, 300, 400);
        it = new MultiIterator(Arrays.asList(list1, list2, list3));
        List<Object[]> list = new ArrayList<Object[]>();
        for (Object a : row(1, 2)) {
            for (Object b : row(10, 20, 30)) {
                for (Object c : row(100, 200, 300, 400)) {
                    list.add(row(a, b, c));
                }
            }
        }
        assertIteratorEquals(list.iterator(), it);
    }

    @Test
    public void testFourLevels() {
        list1 = Arrays.asList(1, 2);
        list2 = Arrays.asList(10, 20, 30);
        list3 = Arrays.asList(100, 200, 300, 400);
        list4 = Arrays.asList(1000, 2000, 3000, 4000, 5000);
        it = new MultiIterator(Arrays.asList(list1, list2, list3, list4));

        List<Object[]> list = new ArrayList<Object[]>();
        for (Object a : row(1, 2)) {
            for (Object b : row(10, 20, 30)) {
                for (Object c : row(100, 200, 300, 400)) {
                    for (Object d : row(1000, 2000, 3000, 4000, 5000)) {
                        list.add(row(a, b, c, d));
                    }
                }
            }
        }
        assertIteratorEquals(list.iterator(), it);
    }

    @Test
    public void testFourLevels2() {
        list1 = new ArrayList<Integer>(100);
        for (int i = 0; i < 100; i++)
            list1.add(i + 1);
        list2 = list1;
        it = new MultiIterator(Arrays.asList(list1, list2));
        while (it.hasNext()) {
            it.next();
        }
    }

}
