package ru.otus;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void runTestsFromClass(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        int passed = 0;
        int failed = 0;
        Method beforeMethod = null;
        Method afterMethod = null;
        List<Method> testMethods = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else {
                if (method.isAnnotationPresent(Before.class)) {
                    beforeMethod = method;
                } else {
                    if (method.isAnnotationPresent(After.class)) {
                        afterMethod = method;
                    }
                }
            }
        }

        if (!testMethods.isEmpty()) {
            if (beforeMethod != null) {
                try {
                    beforeMethod.invoke(null);
                } catch (Exception e) {
                    System.out.println("Before method failed. " + e.getCause().getMessage());
                }
            }
            for (Method method : testMethods) {
                try {
                    method.invoke(null);
                    passed++;
                } catch (Exception e) {
                    System.out.println(e.getCause().getMessage());
                    failed++;
                }
            }
            if (afterMethod != null) {
                try {
                    afterMethod.invoke(null);
                } catch (Exception e) {
                    System.out.println("After method failed. " + e.getCause().getMessage());
                }
            }
        }
        System.out.println("Results of testing:");
        System.out.println("Test passed = " + passed);
        System.out.println("Test failed = " + failed);
    }


    public static void runTestsFromPackage(String packageName) throws ClassNotFoundException {
        Class<?> clazz;
        Path pathToPackage = Paths.get("").toAbsolutePath().getParent()
                .resolve("HW04/src/main/java")
                .resolve(packageName.replace(".", "\\"));
        File[] files = Paths.get(pathToPackage.toString())
                .toFile()
                .listFiles();
        if (files != null) {
            for (File file : files) {
                clazz = Class.forName(packageName + "." + file.getName().replace(".java", ""));
                TestRunner.runTestsFromClass(clazz);
            }
        }
    }
}
