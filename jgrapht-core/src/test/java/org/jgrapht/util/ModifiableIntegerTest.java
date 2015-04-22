

package org.jgrapht.util;

import java.util.*;

import junit.framework.*;


public class ModifiableIntegerTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------

    int i0 = 0;
    int i1 = 1;
    ModifiableInteger mi0 = new ModifiableInteger(0);

    public void testsetGetValue()
    {
        mi0.setValue(i1);
        assertEquals(i1, mi0.getValue());
    }

    public void testincrement()
    {
        mi0.setValue(i1);
        mi0.increment();
        assertEquals(i1 + 1, mi0.getValue());
    }

    public void testdecrement()
    {
        mi0.setValue(i1);
        mi0.decrement();
        assertEquals(i1 - 1, mi0.getValue());
    }
    

}

