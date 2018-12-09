package otus;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectionHelper {

    private static Map<String, List<Field>> classFields = new HashMap<>();

    public static List<Field> getClassFields(Object object) {
        if (object != null) {
            Class clazz = object.getClass();
            if (classFields.containsKey(clazz.getName())) {
                return classFields.get(clazz.getName());
            } else {
                List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());
                classFields.put(clazz.getName(), fieldList);
                return fieldList;
            }
        } else {
            System.err.println("Object is null!");
            return new ArrayList<>();
        }
    }

    public static void setField(Object object, String fieldName, Object fieldValue) throws IllegalAccessException, NoSuchFieldException {
        if (object != null) {
            Field field;
            Class clazz = object.getClass();
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                field = clazz.getSuperclass().getDeclaredField(fieldName);
            }
            field.setAccessible(true);
            field.set(object, fieldValue);
        } else {
            System.err.println("Object is null!");
        }
    }
}

