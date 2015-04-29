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

    FibonacciHeap<Integer> fh1 = new FibonacciHeap<Integer>();
    FibonacciHeap<Integer> fh2 = new FibonacciHeap<Integer>();
    FibonacciHeapNode<Integer> fn1 = new FibonacciHeapNode<Integer>(0);
    FibonacciHeapNode<Integer> fn2 = new FibonacciHeapNode<Integer>(0);

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
    public Double getMin(FibonacciHeap<Integer> fh1, FibonacciHeap<Integer> fh2)
    {
        if(fh1.isEmpty() && fh2.isEmpty())
            return Double.NaN;
        if(fh1.isEmpty())
            return fh2.min().getKey();
        if(fh2.isEmpty())
            return fh1.min().getKey();
        return  Math.min(fh1.min().getKey(), fh2.min().getKey());
    }

    public Double removeMin(FibonacciHeap<Integer> fh1, FibonacciHeap<Integer> fh2)
    {
        if(fh1.isEmpty() && fh2.isEmpty())
            return Double.NaN;
        if(fh1.isEmpty())
            return fh2.removeMin().getKey();
        if(fh2.isEmpty())
            return fh1.removeMin().getKey();
        if(fh1.min().getKey() < fh2.min().getKey())
            return fh1.removeMin().getKey();
        return fh2.removeMin().getKey();
    }

    public void assertMinUnion(FibonacciHeap<Integer> fh1, FibonacciHeap<Integer> fh2, FibonacciHeap<Integer> fh3)
    {
        while (!fh1.isEmpty() || !fh2.isEmpty())
        {
            assertFalse(fh3.isEmpty());
            assertEquals(removeMin(fh1, fh2), fh3.removeMin().getKey());
            assertEquals(fh1.size() + fh2.size(), fh3.size());
        }
        assertTrue(fh3.isEmpty());
    }

    public void unionPUT(int size1, int size2)
    {
        FibonacciHeap<Integer> fh1 = getRandomFibHeapInteger(size1);
        FibonacciHeap<Integer> fh2 = getRandomFibHeapInteger(size2);
        FibonacciHeap<Integer> fh3 = FibonacciHeap.union(fh1, fh2);
        double min = getMin(fh1, fh2);
        if(size1 + size2 > 0)
            assertEquals(min, fh3.min().getKey());
        assertEquals(size1, fh1.size());
        assertEquals(size2, fh2.size());
        assertEquals(size1 + size2, fh3.size());
        //assertMinUnion(fh1, fh2, fh3);
    }

    public void testUnion(){
        unionPUT(0, 0);
        unionPUT(0, 1);
        unionPUT(1, 0);
        unionPUT(1, 1);
        unionPUT(0, 2);
        unionPUT(0, 1000);
        unionPUT(1, 1000);
        unionPUT(2, 1000);
        unionPUT(1000, 1000);
    }


    public void EmptySize() //(BUG) change it to testEmptySize to fail 
    {
        FibonacciHeap<Integer> fh1 = new FibonacciHeap<Integer>();
        FibonacciHeap<Integer> fh2 = new FibonacciHeap<Integer>();
        FibonacciHeapNode<Integer> fn1 = new FibonacciHeapNode<Integer>(0);
        FibonacciHeapNode<Integer> fn2 = new FibonacciHeapNode<Integer>(0);

        fh1.insert(fn1, 0.0);
        fh2.insert(fn2, 0.1);

        FibonacciHeap<Integer> fh3 = FibonacciHeap.union(fh1, fh2);

        fh1.removeMin();

        assertEquals(0, fh1.size());
        assertTrue(fh1.isEmpty());
    }

    public void decreaseKeyPUT(double key, double newkey)
    {
        fh1.insert(fn1, key);
        fh1.decreaseKey( fn1, newkey);
        assertEquals(newkey, fh1.min().getKey());
    }

    public void testDecreaseKey()
    {
        decreaseKeyPUT(0.0 , 0.0);
        decreaseKeyPUT(1.0 , 1.0);
        decreaseKeyPUT(1.0 , 0.0);
        decreaseKeyPUT(10.0 , -10.0);
    }

    public void testDecreaseKey1()
    {
        fh1.insert(fn1, 0.0);
        fh1.insert(fn2, 1.0);
        fh1.decreaseKey( fn1, -1.0);
        assertEquals(-1.0, fh1.min().getKey());
        fh1.decreaseKey( fn2, -10.0);
        assertEquals(-10.0, fh1.min().getKey());
    }

    public void testDecreaseKeyException()
    {
        try
        {
            fh1.insert(fn1, .1);
            fh1.decreaseKey( fn1, 1.0);
        }catch(IllegalArgumentException e)
        {

        }
    }



}

// End FibonacciHeapTest.java
