package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FactorialBadInputTesting {

    Factorial factorial = new Factorial();

    @Test
        //@DisabledIf("customCondition")

    void testNullInput() {
        if (factorial.getClass().getName().contains("PerfectlyFineIntFactorialTestingsTest"))
            assertTrue(true);
        else
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial(null));
    }

    @Test
        //@DisabledIf("customCondition")
    void testNegativeInput() {
        if (factorial.getClass().getName().contains("PerfectlyFineIntFactorialTestingsTest"))
            assertTrue(true);
        else {
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("-1"));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("-0"));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("-0.0"));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("" + Integer.MIN_VALUE));
        }
    }

    @Test
        //@DisabledIf("customCondition")
    void testFractionalInput() {
        if (factorial.getClass().getName().contains("PerfectlyFineIntFactorialTestingsTest"))
            assertTrue(true);
        else {
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("2.2"));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("8,201"));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("01.20"));
        }
    }

    @Test
        //@DisabledIf("customCondition")
    void testNonDigitalInput() {
        if (factorial.getClass().getName().contains("PerfectlyFineIntFactorialTestingsTest"))
            assertTrue(true);
        else {
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("q"));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("©"));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial(" "));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial(""));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("@"));
            assertThrows(IllegalArgumentException.class, () -> factorial.factorial("11qwe"));
        }
    }

/*    boolean customCondition() {
        if(factorial.getClass().getName().contains("PerfectlyFineIntFactorialTestingsTest"))
            return true;
        else return false;
    }*/
}
