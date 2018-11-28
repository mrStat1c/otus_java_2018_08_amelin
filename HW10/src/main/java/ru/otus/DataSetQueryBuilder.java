package ru.otus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataSetQueryBuilder {


    StringBuilder query;
    List<String> valueList;

    public String getInsertQuery(DataSet dataSet) throws IllegalAccessException, NoSuchFieldException {
        Object value;
        query = new StringBuilder();
        valueList = new ArrayList<>();
        List<String> fieldNames = ReflectionHelper
                .getClassFields(dataSet)
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());
        for (String field : fieldNames) {
            value = ReflectionHelper.getFieldValue(dataSet, field);
            if (value instanceof String) {
                valueList.add("'" + value + "'");
            } else {
                valueList.add(value.toString());
            }
        }
     return query
             .append("INSERT INTO hw10.user (")
             .append(getStringFromList(fieldNames))
             .append(") VALUES (")
             .append(getStringFromList(valueList))
             .append(");")
             .toString();
    }

    private String getStringFromList(List<String> list){
        return list.toString()
                .replace("[", "")
                .replace("]", "");
    }
}
