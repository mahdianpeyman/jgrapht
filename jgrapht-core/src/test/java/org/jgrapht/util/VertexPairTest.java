
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
    VertexPair<String> vp4 = new VertexPair<String>(s1 , s2);
    VertexPair<String> vp2 = new VertexPair<String>(null , null);
    VertexPair<String> vp3 = new VertexPair<String>(null , null);
    VertexPair<String> vp5 = new VertexPair<String>(s1 , null);
    VertexPair<String> vp6 = new VertexPair<String>(s2 , s3);
    VertexPair<String> vp7 = new VertexPair<String>(s1 , s3);

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

    public void testtoString()
    {
        assertEquals(s1+","+s2, vp1.toString());
    }

    public void testequalAndhashCode(){
        Random r = new Random();
        for(int i = 0 ; i < 1000 ; i++){
            Integer a = r.nextInt();
            Integer b = r.nextInt();
            equalsAndHashCodePUT(a, b);
        }
    }

    public void equalsAndHashCodePUT(Integer a, Integer b)
    {

        VertexPair<Integer> vpInt1 = new VertexPair<Integer>(a , b);
        VertexPair<Integer> vpInt2 = new VertexPair<Integer>(vpInt1.getFirst() , vpInt1.getSecond());
        assertTrue(vpInt1.equals(vpInt1));
        assertEquals(vpInt1.hashCode(), vpInt2.hashCode());
    }

    public void testequals1()
    {
        assertTrue(vp1.equals(new VertexPair<String>(vp1.getFirst() , vp1.getSecond())));
    }

    public void testequals2()
    {
        assertTrue(vp1.equals(vp1));
    }

    public void testequals3()
    {
        assertFalse(vp1.equals(null));
    }

    public void testequals4()
    {
        assertFalse(vp1.equals(s1));
    }

    public void testequals5()
    {
        assertFalse(vp2.equals(vp1));
    }

    public void testequals6()
    {
        assertTrue(vp2.equals(vp3));
    }

    public void testequals7()
    {
        assertFalse(vp5.equals(vp3));
    }

    public void testequals8()
    {
        assertFalse(vp1.equals(vp6));
    }

    public void testequals9()
    {
        assertFalse(vp5.equals(vp7));
    }

    public void testhashCode1()
    {
        assertEquals(vp2.hashCode(), vp3.hashCode());
    }

    public void testhashCode2()
    {
        assertEquals(vp1.hashCode(), vp4.hashCode());
    }


}

