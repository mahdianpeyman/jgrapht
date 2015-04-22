

package org.jgrapht.util;

import java.util.*;

import junit.framework.*;


public class ModifiableIntegerTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------

    Integer I1 = 1;
    int i0 = 0;
    int i1 = 1;
    double d1 = 1.0;
    float f1 = 1.0f;
    long l1 = 1L;
    ModifiableInteger mi0 = new ModifiableInteger(i0);
    ModifiableInteger mi00 = new ModifiableInteger(i0);
    ModifiableInteger mi1 = new ModifiableInteger(i1);

    public void testsetGetValue()
    {
        Random r = new Random();
        for(int i = 0 ; i < 1000 ; i++){
            Integer a = r.nextInt();
            setGetPUT(a);
        }
    }
    public void testequalAndhashCode(){
        Random r = new Random();
        for(int i = 0 ; i < 1000 ; i++){
            Integer a = r.nextInt();
            Integer b = r.nextInt();
            equalsAndHashCodePUT(a, b);
        }
    }

    public void testincrement()
    {
        mi0.setValue(i1);
        mi0.increment();
        assertEquals(i1 + 1, mi0.getValue());
    }



    public void setGetPUT(int a){
        mi0.setValue(a);
        assertEquals(a, mi0.getValue());
    }

    public void testdecrement()
    {
        mi0.setValue(i1);
        mi0.decrement();
        assertEquals(i1 - 1, mi0.getValue());
    }

    public void testcompareTo1()
    {
        assertEquals(-1, mi0.compareTo(mi1));
    }

    public void testcompareTo2()
    {
        assertEquals(1, mi1.compareTo(mi0));
    }

    public void testcompareTo3()
    {
        assertEquals(0, mi0.compareTo(mi0));
    }

    public void testequals1()
    {
        assertTrue(mi0.equals(mi0));
    }

    public void testequals2()
    {
        assertFalse(mi0.equals(mi1));
    }

    public void testequals3()
    {
        assertFalse(mi0.equals(i1));
    }

    public void testdoubleValue()
    {
        assertEquals(d1, mi1.doubleValue());
    }

    public void testfloatValue()
    {
        assertEquals(f1, mi1.floatValue());
    }

    public void testintValue()
    {
        assertEquals(i1, mi1.intValue());
    }

    public void testlongValue()
    {
        assertEquals(l1, mi1.longValue());
    }

    public void testtoInteger()
    {
        assertEquals(I1, mi1.toInteger());
    }

    public void testtoString()
    {
        assertEquals(I1.toString(), mi1.toString());
    }

    public void testhashCode1()
    {
        assertEquals(mi0.hashCode(), mi00.hashCode());
    }

    public void equalsAndHashCodePUT(int a, int b)
    {
        ModifiableInteger mia = new ModifiableInteger(a);
        ModifiableInteger mib = new ModifiableInteger(b);

        assertEquals(mia.hashCode() == mib.hashCode(), mia.equals(mib));
    }
    

}

