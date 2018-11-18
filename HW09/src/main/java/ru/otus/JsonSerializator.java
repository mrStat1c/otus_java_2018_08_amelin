package ru.otus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

public class JsonSerializator {

    public static String convertToJsonString(Object object) throws IllegalAccessException, InstantiationException {
//        JsonObject jsonObject = Json.createObjectBuilder().build();
        JSONObject jsonObject = new JSONObject();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (isString(object, field)) {
                jsonObject.put(field.getName(), field.get(object));
            } else if (isInt(object, field)) {
                jsonObject.put(field.getName(), field.getInt(object));
            } else if (isBoolean(object, field)) {
                jsonObject.put(field.getName(), field.getBoolean(object));
            } else if (isDouble(object, field)) {
                jsonObject.put(field.getName(), field.getDouble(object));
            } else if (isByte(object, field)) {
                jsonObject.put(field.getName(), field.getByte(object));
            } else if (isLong(object, field)) {
                jsonObject.put(field.getName(), field.getLong(object));
            } else if (isShort(object, field)) {
                jsonObject.put(field.getName(), field.getShort(object));
            } else if (isFloat(object, field)) {
                jsonObject.put(field.getName(), field.getFloat(object));
            } else if (isChar(object, field)) {
                jsonObject.put(field.getName(), field.getChar(object));
            } else if (isArray(object, field)) {
                Class clazz = field.getType().getComponentType();
                if (isPrimitive(clazz)) {
                    JSONArray jsonArray = new JSONArray();
                    Object[] x;
                    if (clazz.newInstance() instanceof Number) {
                        x = (Number[]) field.get(object);
                    } else if (clazz.newInstance() instanceof Boolean) {
                        x = (Boolean[]) field.get(object);
                    } else {
                        x = (String[]) field.get(object);
                    }
                    jsonArray.addAll(Arrays.asList(x));
                    jsonObject.put(field.getName(), jsonArray);
                }
            }
        }
        return jsonObject.toString();
    }


    private static boolean isString(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof String;
    }

    private static boolean isInt(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Integer;
    }

    private static boolean isBoolean(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Boolean;
    }

    private static boolean isDouble(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Double;
    }

    private static boolean isByte(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Byte;
    }

    private static boolean isLong(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Long;
    }

    private static boolean isShort(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Short;
    }

    private static boolean isFloat(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Float;
    }

    private static boolean isChar(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Character;
    }

    private static boolean isArray(Object object, Field field) throws IllegalAccessException {
        return field.get(object).getClass().isArray();
    }

    private static boolean isPrimitive(Class clazz) throws IllegalAccessException, InstantiationException {
        Object obj = clazz.newInstance();
        return obj instanceof String || obj instanceof Number || obj instanceof Boolean;
    }
}
