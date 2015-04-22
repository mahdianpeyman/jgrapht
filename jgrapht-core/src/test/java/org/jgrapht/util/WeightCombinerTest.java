

package org.jgrapht.util;

import java.util.*;

import junit.framework.*;


public class WeightCombinerTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------

    public void testAll(){
        Random r = new Random();
        for(int i = 0; i < 1000 ; i++){
            Double a = r.nextDouble();
            Double b = r.nextDouble();
            sumFirstSecondPUT(a, b);
            sumFirstSecondPUT(a, a);
            minMaxPUT(a, b);
            minMaxPUT(a, a);
        }
    }


    public void sumFirstSecondPUT(Double a, Double b){
        assertEquals(a + b, WeightCombiner.SUM.combine(a, b));
        assertEquals(a , WeightCombiner.FIRST.combine(a, b));
        assertEquals(b , WeightCombiner.SECOND.combine(a, b));
    }

    public void minMaxPUT(Double a, Double b){
        if(a > b){
            assertEquals(b , WeightCombiner.MIN.combine(a, b));
            assertEquals(a , WeightCombiner.MAX.combine(a, b));
        }else if (a < b){
            assertEquals(a , WeightCombiner.MIN.combine(a, b));
            assertEquals(b , WeightCombiner.MAX.combine(a, b));
        }else{
            assertEquals(a , WeightCombiner.MIN.combine(a, b));
            assertEquals(b , WeightCombiner.MAX.combine(a, b));
            assertEquals(b , WeightCombiner.MIN.combine(a, b));
            assertEquals(a , WeightCombiner.MAX.combine(a, b));
        }
    }

}

