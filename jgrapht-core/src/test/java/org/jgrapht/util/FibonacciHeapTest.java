/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
/* -----------------
 * FibonacciHeapTest.java
 * -----------------
 * (C) Copyright 2008-2008, by John V. Sichi and Contributors.
 *
 * Original Author:  John V. Sichi
 * Contributor(s):   -
 *
 * $Id$
 *
 * Changes
 * -------
 * 20-Apr-2008 : Initial revision (JVS);
 */
package org.jgrapht.util;

import java.util.*;

import junit.framework.*;


public class FibonacciHeapTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------

    // in honor of sf.net bug #1845376
    public void testAddRemoveOne()
    {
        String s = "A";
        FibonacciHeapNode<String> n = new FibonacciHeapNode<String>(s);
        FibonacciHeap<String> h = new FibonacciHeap<String>();
        assertTrue(h.isEmpty());
        h.insert(n, 1.0);
        assertFalse(h.isEmpty());
        FibonacciHeapNode<String> n2 = h.removeMin();
        assertEquals(s, n2.getData());
        assertTrue(h.isEmpty());
    }

    public void testGrowReplaceShrink()
    {
        Random r = new Random();
        int k = 10000;
        String s = "A";
        double t = 0;
        FibonacciHeap<String> h = new FibonacciHeap<String>();
        for (int i = 0; i < (k * 3); ++i) {
            // during first two-thirds, insert
            if (i < (k * 2)) {
                double d = r.nextDouble();
                t += d;
                FibonacciHeapNode<String> n = new FibonacciHeapNode<String>(s);
                h.insert(n, d);
            }

            // during last two-thirds, delete (so during middle
            // third, we'll do both insert and delete, interleaved)
            if (i >= k) {
                FibonacciHeapNode<String> n2 = h.removeMin();
                t -= n2.getKey();
            }
        }
        assertTrue(h.isEmpty());

        // tally should come back down to zero, or thereabouts (due to roundoff)
        assertEquals(0.0, t, 0.00001);
    }

    public FibonacciHeap<Integer> getRandomFibHeapInteger(Integer size)
    {
        Random r = new Random();
        FibonacciHeap<Integer> fh = new FibonacciHeap<Integer>();
        for(int i = 0 ; i < size ; i++){
            double d = r.nextDouble();
            Integer s = r.nextInt();
            FibonacciHeapNode<Integer> fn = new FibonacciHeapNode<Integer>(s);
            fh.insert(fn, d);
        }
        return fh;
    }

    public void unionPUT(int size1, int size2)
    {
        FibonacciHeap<Integer> fb1 = getRandomFibHeapInteger(size1);
        FibonacciHeap<Integer> fb2 = getRandomFibHeapInteger(size2);
        FibonacciHeap<Integer> fb3 = FibonacciHeap.union(fb1, fb2);
        double min = Math.min(fb1.min().getKey(), fb2.min().getKey());
        assertEquals(min, fb3.min().getKey());
    }

    public void testUnion(){
        unionPUT(1, 1);
    }



}

// End FibonacciHeapTest.java
