
package org.jgrapht.alg.util;

import java.io.*;

import junit.framework.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;

public class VertexDegreeComparatorTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------

    /**
     * .
     */
    SimpleGraph<Integer, DefaultEdge> g0 = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
    VertexDegreeComparator<Integer, DefaultEdge> compT = new VertexDegreeComparator<Integer, DefaultEdge>(g0, true);
    VertexDegreeComparator<Integer, DefaultEdge> compF = new VertexDegreeComparator<Integer, DefaultEdge>(g0, false);



    protected void setUp(){
        g0.addVertex(0);
        g0.addVertex(1);
        g0.addVertex(2);
        g0.addVertex(3);
        g0.addEdge(0, 2);   //      1 - 3           deg(1) = 1
        g0.addEdge(0, 3);   //         / \          deg(0) = deg(2) = 2
        g0.addEdge(1, 3);   //        0 - 2         deg(3) = 3
        g0.addEdge(2, 3);   // 
    }

    public void testTTF(){     
        assertEquals(-1, compT.compare(1, 2));
    }
    public void testTFF(){     
        assertEquals(1, compF.compare(1, 3));
    }
    public void testFTT(){     
        assertEquals(1, compT.compare(3, 2));
    }
    public void testFTF(){     
        assertEquals(0, compT.compare(2, 0));
    }
    public void testFFT(){     
        assertEquals(-1, compF.compare(3, 1));
    }
    public void testFFF(){     
        assertEquals(0, compF.compare(0, 2));
    }
   
}

// End UnionFindTest.java
