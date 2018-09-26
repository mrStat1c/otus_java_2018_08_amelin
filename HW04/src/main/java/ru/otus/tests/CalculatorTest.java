package ru.otus.tests;

import ru.otus.After;
import ru.otus.Before;
import ru.otus.Calculator;
import ru.otus.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorTest {

    @Before
    public static void before(){
        System.out.println("Testing started.");
    }

    @After
    public static void after(){
        System.out.println("Testing finished.");
    }

    @Test
    public static void testSum(){
        assertThat("Test of function \"testSum\" failed!", Calculator.sum(12, 35) == 47 );
    }

    @Test
    public static void testMinus(){
        assertThat("Test of function \"testMinus\" failed!", Calculator.minus(40, 20) == 30 );
    }

    @Test
    public static void testMultiply(){
        assertThat("Test of function \"testMultiply\" failed!", Calculator.multiply(5, 5) == 25);
    }

    @Test
    public static void testDivide(){
        assertThat("Test of function \"testDivide\" failed!", Calculator.divide(15, 3) == 5 );
    }
}
