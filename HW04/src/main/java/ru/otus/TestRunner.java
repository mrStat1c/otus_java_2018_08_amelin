package ru.otus;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
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
        Package p = ClassLoader.getSystemClassLoader().getDefinedPackage("ru.otus.tests");
        int x = 1;
//        File[] files = Paths
//                .get(ClassLoader.getSystemClassLoader().getDefinedPackage() + packageName.replace(".", "\\"))
//                .toFile()
//                .listFiles();
//        if (files != null) {
//            for (File file : files) {
//                clazz = Class.forName(packageName + "." + file.getName().replace(".java", ""));
//                TestRunner.runTestsFromClass(clazz);
//            }
//        }
    }
}
