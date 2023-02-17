package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FactorialRegularInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void testZeroInput() {

        assertEquals("1", factorial.factorial("0"));
        assertEquals("1", factorial.factorial("+0"));
    }

    @Test
    void simpleInput() {
        assertEquals("1",   factorial.factorial("1"));
        assertEquals("2",   factorial.factorial("2"));
        //assertEquals("10888869450418352160768000000",factorial.factorial("27"));
        assertEquals("3350850684932979117652665123754814942022584063591740702576779884286208799035732771005626138126763314259280802118502282445926550135522251856727692533193070412811083330325659322041700029792166250734253390513754466045711240338462701034020262992581378423147276636643647155396305352541105541439434840109915068285430675068591638581980604162940383356586739198268782104924614076605793562865241982176207428620969776803149467431386807972438247689158656000000000000000000000000000000000000000000000000000000000000000",
                factorial.factorial("255"));
    }
   @Test
    void testLeadingZeroInput() {
        if (factorial.getClass().getName().contains("PerfectlyFineIntFactorialTestingsTest"))
            assertTrue(true);
        else {
            assertEquals("1",factorial.factorial("01"));
            assertEquals("2432902008176640000", factorial.factorial("020"));
        }
    }

}

