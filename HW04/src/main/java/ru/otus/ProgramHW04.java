package ru.otus;

import ru.otus.tests.CalculatorTest;

public class ProgramHW04 {

    public static void main(String[] args) throws ClassNotFoundException {
        TestRunner.runTests(CalculatorTest.class);
        TestRunner.runTestsFromPackage("ru.otus.tests");
    }

}
