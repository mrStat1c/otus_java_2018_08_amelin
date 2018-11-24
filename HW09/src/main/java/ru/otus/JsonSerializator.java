package ru.otus;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class JsonSerializator {

    public static String getJsonString(Object object) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        return convertToJsonObject(object).toString();
    }

    private static JSONObject convertToJsonObject(Object object) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject();
        if (object != null) {
                for (Field field : object.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (isNull(object, field)) {
                        jsonObject.put(field.getName(), null);
                    } else {
                        Object val = field.get(object);
                        if (val.getClass().isPrimitive() || isPrimitiveWrapper(val)) {
                            jsonObject.put(field.getName(), val);
                        } else if (isArray(object, field)) {
                            Class clazz = field.getType().getComponentType();
                            JSONArray jsonArray = new JSONArray();
                            Object[] objects;
                            if (clazz.isPrimitive()) {
                                if (clazz.equals(int.class)) {
                                    int[] intArray = (int[]) field.get(object);
                                    for (int i = 0; i < intArray.length; i++) {
                                        jsonArray.add(intArray[i]);
                                    }
                                } else if (clazz.equals(byte.class)) {
                                    byte[] intArray = (byte[]) field.get(object);
                                    for (int i = 0; i < intArray.length; i++) {
                                        jsonArray.add(intArray[i]);
                                    }
                                } else if (clazz.equals(short.class)) {
                                    short[] intArray = (short[]) field.get(object);
                                    for (int i = 0; i < intArray.length; i++) {
                                        jsonArray.add(intArray[i]);
                                    }
                                } else if (clazz.equals(long.class)) {
                                    long[] intArray = (long[]) field.get(object);
                                    for (int i = 0; i < intArray.length; i++) {
                                        jsonArray.add(intArray[i]);
                                    }
                                } else if (clazz.equals(float.class)) {
                                    float[] intArray = (float[]) field.get(object);
                                    for (int i = 0; i < intArray.length; i++) {
                                        jsonArray.add(intArray[i]);
                                    }
                                } else if (clazz.equals(double.class)) {
                                    double[] intArray = (double[]) field.get(object);
                                    for (int i = 0; i < intArray.length; i++) {
                                        jsonArray.add(intArray[i]);
                                    }
                                } else if (clazz.equals(boolean.class)) {
                                    boolean[] intArray = (boolean[]) field.get(object);
                                    for (int i = 0; i < intArray.length; i++) {
                                        jsonArray.add(intArray[i]);
                                    }
                                } else if (clazz.equals(char.class)) {
                                    char[] intArray = (char[]) field.get(object);
                                    for (int i = 0; i < intArray.length; i++) {
                                        jsonArray.add(intArray[i]);
                                    }
                                }
                                jsonObject.put(field.getName(), jsonArray);
                                continue;
                            } else if (isPrimitiveWrapper(clazz)) {
                                if (Number.class.isAssignableFrom(clazz)) {
                                    objects = (Number[]) field.get(object);
                                } else if (Boolean.class.isAssignableFrom(clazz)) {
                                    objects = (Boolean[]) field.get(object);
                                } else {
                                    objects = (String[]) field.get(object);
                                }
                            } else {
                                objects = (Object[]) field.get(object);
                                for (int j = 0; j < objects.length; j++) {
                                    objects[j] = convertToJsonObject(objects[j]);
                                }
                            }
                            jsonArray.addAll(Arrays.asList(objects));
                            jsonObject.put(field.getName(), jsonArray);
                        } else if (isList(object, field)) {
                            String parameterTypeName = ((ParameterizedType) field.getGenericType())
                                    .getActualTypeArguments()[0]
                                    .getTypeName();
                            Class clazz = Class.forName(parameterTypeName);
                            JSONArray jsonArray = new JSONArray();
                            Object[] objects = ((List<?>) field.get(object)).toArray();
                            if (!isPrimitiveWrapper(clazz)) {
                                for (int j = 0; j < objects.length; j++) {
                                    objects[j] = convertToJsonObject(objects[j]);
                                }
                            }
                            jsonArray.addAll(Arrays.asList(objects));
                            jsonObject.put(field.getName(), jsonArray);
                        } else if (isSet(object, field)) {
                            String parameterTypeName = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName();
                            Class clazz = Class.forName(parameterTypeName);
                            JSONArray jsonArray = new JSONArray();
                            Object[] x = ((Set<?>) field.get(object)).toArray();
                            if (!isPrimitiveWrapper(clazz)) {
                                for (int j = 0; j < x.length; j++) {
                                    x[j] = convertToJsonObject(x[j]);
                                }
                            }
                            jsonArray.addAll(Arrays.asList(x));
                            jsonObject.put(field.getName(), jsonArray);
                        } else if (isMap(object, field)) {
                            Map<?, ?> map = (Map<?, ?>) field.get(object);
                            Set<? extends Map.Entry<?, ?>> entries = map.entrySet();
                            List<Object> keys = new ArrayList<>();
                            List<Object> values = new ArrayList<>();
                            entries.forEach(entry -> {
                                keys.add(entry.getKey());
                                values.add(entry.getValue());
                            });
                            Class keyClazz = keys.get(0).getClass();
                            Class valueClazz = values.get(0).getClass();
                            Object[] keyArray = keys.toArray();
                            Object[] valueArray = values.toArray();
                            JSONArray jsonArray = new JSONArray();
                            if (!isPrimitiveWrapper(keyClazz)) {
                                for (int j = 0; j < keyArray.length; j++) {
                                    keyArray[j] = convertToJsonObject(keyArray[j]);
                                }
                            }
                            if (!isPrimitiveWrapper(valueClazz)) {
                                for (int j = 0; j < valueArray.length; j++) {
                                    valueArray[j] = convertToJsonObject(valueArray[j]);
                                }
                            }
                            for (int j = 0; j < keyArray.length; j++) {
                                JSONObject localJson = new JSONObject();
                                localJson.put(keyArray[j], valueArray[j]);
                                jsonArray.add(localJson);
                            }
                            jsonObject.put(field.getName(), jsonArray);
                        }
                    }
                }
            }
        return jsonObject;
    }

    private static boolean isNull(Object object, Field field) throws IllegalAccessException {
        return field.get(object) == null;

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

    private static boolean isPrimitiveWrapper(Class clazz) {
        return String.class.isAssignableFrom(clazz)
                || Number.class.isAssignableFrom(clazz)
                || Boolean.class.isAssignableFrom(clazz);
    }

    private static boolean isPrimitiveWrapper(Object object) {
        return object instanceof String
                || object instanceof Integer
                || object instanceof Boolean
                || object instanceof Double
                || object instanceof Byte
                || object instanceof Long
                || object instanceof Short
                || object instanceof Float
                || object instanceof Character;
    }

    private static boolean isList(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof List;
    }

    private static boolean isSet(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Set;
    }

    private static boolean isMap(Object object, Field field) throws IllegalAccessException {
        return field.get(object) instanceof Map;
    }


}
