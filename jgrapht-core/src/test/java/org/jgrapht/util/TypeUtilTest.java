

package org.jgrapht.util;

import java.util.*;

import junit.framework.*;



public class TypeUtilTest
    extends TestCase
{
    //~ Methods ----------------------------------------------------------------
    TypeUtil<String> string_type0 = null;
    TypeUtil<Integer> int_type0 = null;
    TypeUtil<Boolean> bool_type0 = null;

    TypeUtil<String> string_type1 = new TypeUtil<String>();
    TypeUtil<Integer> int_type1 = new TypeUtil<Integer>();
    TypeUtil<Boolean> bool_type1 = new TypeUtil<Boolean>();


    public void testuncheckedCastString1(){
        assertTrue(string_type0.uncheckedCast("", string_type0) instanceof String);
    }

    public void testuncheckedCastString2(){
        assertTrue(string_type1.uncheckedCast("String", string_type0) instanceof String);
    }

    public void testuncheckedCastString3(){
        assertTrue(string_type0.uncheckedCast("123141", string_type1) instanceof String);
    }

    public void testuncheckedCastInt1(){
        assertTrue(int_type0.uncheckedCast(1, int_type0) instanceof Integer);
    }

    public void testuncheckedCastInt2(){
        assertTrue(int_type1.uncheckedCast(-1, int_type0) instanceof Integer);
    }

    public void testuncheckedCastInt3(){
        assertTrue(int_type0.uncheckedCast(0, int_type1) instanceof Integer);
    }

    public void testuncheckedCastBoolean1(){
        assertTrue(bool_type0.uncheckedCast(false, bool_type0) instanceof Boolean);
    }

    public void testuncheckedCastBoolean2(){
        assertTrue(bool_type1.uncheckedCast(true, bool_type0) instanceof Boolean);
    }

    public void testuncheckedCastBoolean3(){
        assertTrue(bool_type0.uncheckedCast(false, bool_type1) instanceof Boolean);
    }
}
