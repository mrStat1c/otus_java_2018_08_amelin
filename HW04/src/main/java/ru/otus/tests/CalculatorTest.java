package ru.otus.tests;

import ru.otus.After;
import ru.otus.Before;
import ru.otus.Calculator;
import ru.otus.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorTest {

    @Before
    public void before() {
        System.out.println("Test started.");
    }

    @After
    public void after() {
        System.out.println("Test finished.");
    }

    @Test
    public void testSum() {
        assertThat("Test of function \"testSum\" failed!", Calculator.sum(12, 35) == 47);
    }

    @Test
    public void testMinus() {
        assertThat("Test of function \"testMinus\" failed!", Calculator.minus(40, 20) == 30);
    }

    @Test
    public void testMultiply() {
        assertThat("Test of function \"testMultiply\" failed!", Calculator.multiply(5, 5) == 25);
    }

    @Test
    public void testDivide() {
        assertThat("Test of function \"testDivide\" failed!", Calculator.divide(15, 3) == 5);
    }
}
