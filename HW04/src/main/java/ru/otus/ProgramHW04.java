package ru.otus;

import ru.otus.tests.CalculatorTest;

import java.lang.reflect.InvocationTargetException;

public class ProgramHW04 {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestRunner.runTestsFromClass(CalculatorTest.class);
        TestRunner.runTestsFromPackage("ru.otus.tests");
    }

}
