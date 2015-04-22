
package org.jgrapht.util;

import java.util.*;

import junit.framework.*;


public class VertexPairTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------
    String s1 = "A";
    String s2 = "B";
    String s3 = "C";
    VertexPair<String> vp1 = new VertexPair<String>(s1 , s2);

    public void testgetFirstAndSecond()
    {
        assertEquals(s1, vp1.getFirst());
        assertEquals(s2, vp1.getSecond());
    }

    public void testhasVertex()
    {
        assertTrue(vp1.hasVertex(s1));
        assertTrue(vp1.hasVertex(s2));
        assertFalse(vp1.hasVertex(s3));
    }

    public void testgetOther()
    {
        assertEquals(s1, vp1.getOther(s2));
        assertEquals(s2, vp1.getOther(s1));
        assertEquals(null, vp1.getOther(s3));
    }
}

