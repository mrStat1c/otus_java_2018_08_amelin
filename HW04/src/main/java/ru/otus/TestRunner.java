package ru.otus;

import org.reflections.Reflections;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Set;

public class TestRunner {

    public static void runTests(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        int passed = 0;
        int failed = 0;

        for (int i = 0; i < methods.length; i++) {
            Test test = methods[i].getDeclaredAnnotation(Test.class);
            if (test != null) {
                try {
                    methods[i].invoke(null);
                    passed++;
                } catch (Exception e) {
                    System.out.println(e.getCause().getMessage());
                    failed++;
                }
            }
        }
        System.out.println("Test passed = " + passed);
        System.out.println("Test failed = " + failed);
    }

    public static void runTestsFromPackage(String packageName) throws ClassNotFoundException {
        Class<?> clazz;
        File[] files = Paths
                .get("C:\\IdeaProjects\\javatrainingproject\\HW04\\src\\main\\java\\ru\\otus\\tests")
                .toFile()
                .listFiles();
        if (files != null) {
            for (File file : files) {
                clazz = Class.forName(packageName + "." + file.getName().replace(".java", ""));
                TestRunner.runTests(clazz);
            }
        }
    }
}
