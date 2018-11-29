package ru.otus;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionHelper {

    private static Map<String, List<Field>> classFields = new HashMap<>();

    public static List<Field> getClassFields(Object object) {
        Class clazz = object.getClass();
        if (classFields.containsKey(clazz.getName())) {
            return classFields.get(clazz.getName());
        } else {
            List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());
            classFields.put(clazz.getName(), fieldList);
            return fieldList;
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

    public static boolean isString(Field field){
        return String.class.isAssignableFrom(field.getType());
    }

    public static boolean isInt(Field field){
        return int.class.isAssignableFrom(field.getType())
                || Integer.class.isAssignableFrom(field.getType());
    }

    public static boolean isLong(Field field){
        return long.class.isAssignableFrom(field.getType())
                || Long.class.isAssignableFrom(field.getType());
    }
}

