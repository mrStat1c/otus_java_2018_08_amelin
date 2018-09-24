package ru.otus;

import java.lang.reflect.Method;

public class TestAnnotationAnalyzer {
    public static void test(Class<?> clazz){
        Method[] methods = clazz.getClass().getDeclaredMethods();
        int passed = 0;
        int failed = 0;

        for (int i = 0; i < methods.length; i++){
            System.out.println(methods[i]);
            Test test = methods[i].getDeclaredAnnotation(Test.class);
            if (test != null){
                try{
                    methods[i].invoke(null);
                    passed++;
                }catch (Exception e){
                    failed++;
                }
            }
        }
        System.out.println(passed);
        System.out.println(failed);
    }
}
