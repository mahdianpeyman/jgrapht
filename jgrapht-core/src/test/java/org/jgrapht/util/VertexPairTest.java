
package org.jgrapht.util;

import java.util.*;

import junit.framework.*;


public class VertexPairTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------
    String s1 = "A";
    String s2 = "B";

    public void testgetFirstAndSecond()
    {
        VertexPair<String> vp1 = new VertexPair<String>(s1 , s2);

        assertEquals(s1, vp1.getFirst());
        assertEquals(s2, vp1.getSecond());
    }
}

