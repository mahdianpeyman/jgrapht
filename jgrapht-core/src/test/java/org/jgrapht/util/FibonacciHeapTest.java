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
    FibonacciHeapNode<Integer> fn0 = new FibonacciHeapNode<Integer>(0);
    FibonacciHeapNode<Integer> fn1 = new FibonacciHeapNode<Integer>(0);
    FibonacciHeapNode<Integer> fn2 = new FibonacciHeapNode<Integer>(0);
    FibonacciHeapNode<Integer> fn3 = new FibonacciHeapNode<Integer>(0);
    FibonacciHeapNode<Integer> fn4 = new FibonacciHeapNode<Integer>(0);
    FibonacciHeapNode<Integer> fn5 = new FibonacciHeapNode<Integer>(0);
    FibonacciHeapNode<Integer> fn6 = new FibonacciHeapNode<Integer>(0);
    FibonacciHeapNode<Integer> fn7 = new FibonacciHeapNode<Integer>(0);

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

    public void testUnion1(){
        FibonacciHeap<Integer> fh = FibonacciHeap.union(null, null);
        assertEquals(0, fh.size());
        assertTrue(fh.isEmpty());
    }

    public void Union2(){           //BUG change it to testUnion2 to fail 
        fh1.insert(fn1, 0.0);
        FibonacciHeap<Integer> fh = FibonacciHeap.union(fh1, null);
        assertEquals(1, fh.size());
        assertFalse(fh.isEmpty());
        fh1.removeMin();
        assertEquals(1, fh.size());
        assertFalse(fh.isEmpty());
    }

    public void testUnion3(){
        FibonacciHeap.union(null, null);
        FibonacciHeap.union(fh1, null);
        FibonacciHeap.union(null, fh1);
    }


    public void EmptySize(){  //(BUG) change it to testEmptySize to fail 
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
        fh1.insert(fn2, 10.0);
        fh1.insert(fn3, -2.0);
        fh1.removeMin();
        fh1.decreaseKey( fn2, 3.0);
        assertEquals(0.0, fh1.min().getKey());
        fh1.decreaseKey( fn2, -3.0);
        assertEquals(-3.0, fh1.min().getKey());
    }

    public void testDecreaseKey2()
    {
        fh1.insert(fn0, 0.0);
        fh1.insert(fn1, 1.0);
        fh1.insert(fn2, 2.0);
        fh1.insert(fn3, 3.0);
        fh1.insert(fn4, 4.0);
        fh1.insert(fn5, 5.0);
        fh1.insert(fn6, 6.0);
        fh1.insert(fn7, -40.0);
        fh1.removeMin();
        fh1.decreaseKey(fn1, -1.0);
        assertEquals(-1.0, fh1.min().getKey());
        //fh1.removeMin();
        fh1.decreaseKey(fn3, -2.0);
        //fh1.decreaseKey( fn5, -3.0);
        // assertEquals(-3.0, fh1.min().getKey());
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

    public void Delete() {      //(BUG) change it to testDelete to fail 
        fh1.insert(fn1, Double.NEGATIVE_INFINITY);
        fh1.insert(fn2, 0.0);
        fh1.delete(fn2);
        assertEquals(fh1.min(), fn1);
    }

    public void testDelete(){
        fh1.insert(fn1, -13.13);
        fh1.insert(fn2, 0.0);
        fh1.delete(fn2);
        assertEquals(fh1.min(), fn1);
    }

    public void testRemoveMin(){
        fh1.removeMin();
    }

    public void testToString(){ // (BUG) prints an extra comma
        assertEquals("FibonacciHeap=[]", fh1.toString());
        fh1.insert(fn1, 0.0);
        assertEquals("FibonacciHeap=[0.0, ]", fh1.toString());
        fh1.insert(fn2, -12.12);
        assertEquals("FibonacciHeap=[-12.12, 0.0, ]", fh1.toString());
        fh1.insert(fn3, 13.13);
        assertEquals("FibonacciHeap=[-12.12, 13.13, 0.0, ]", fh1.toString());
        fh1.insert(fn4, 290.3);
        assertEquals("FibonacciHeap=[-12.12, 290.3, 13.13, 0.0, ]", fh1.toString());
        fh1.removeMin();
        assertEquals("FibonacciHeap=[0.0, 13.13, 290.3, ]", fh1.toString());
    }

    public void SameNode(){ // BUG change it to testSameNode to fail 
        fh1.insert(fn1, 0.0);
        fh1.insert(fn1, 1.0);
        assertEquals(0.0, fh1.min().getKey());
    }


}

// End FibonacciHeapTest.java
