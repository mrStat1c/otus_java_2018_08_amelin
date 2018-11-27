package ru.otus;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionHelper {

    private static Map<String, List<String>> classFields = new HashMap<>();

    public static List<String> getClassFieldNames(Object object) {
        Class clazz = object.getClass();
        if (classFields.containsKey(clazz)) {
            return classFields.get(clazz);
        } else {
            List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());
            List<String> fieldNameList = new ArrayList<>();
            for (Field field : fieldList) {
                field.setAccessible(true);
                fieldNameList.add(field.getName());
            }
            classFields.put(clazz.getName(), fieldNameList);
            return fieldNameList;
        }
    }

    public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    public static void setField (Object object, String fieldName, String fieldValue) throws IllegalAccessException, NoSuchFieldException {
        Field field;
        Class clazz = object.getClass();
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = clazz.getSuperclass().getDeclaredField(fieldName);
        }
        field.setAccessible(true);
        if (String.class.isAssignableFrom(field.getType())) {
            field.set(object, fieldValue);
        } else if (int.class.isAssignableFrom(field.getType())) {
            field.set(object, Integer.parseInt(fieldValue));
        } else if (long.class.isAssignableFrom(field.getType())) {
            field.set(object, Long.parseLong(fieldValue));
        }
    }
}

