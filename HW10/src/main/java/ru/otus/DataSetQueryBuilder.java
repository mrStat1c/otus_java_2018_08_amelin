package ru.otus;

import ru.otus.DataSet;
import ru.otus.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;

public class DataSetQueryBuilder {


    StringBuilder query = new StringBuilder();
    List<String> valueList = new ArrayList<>();

    public String getInsertQuery(DataSet dataSet) throws IllegalAccessException, NoSuchFieldException {
        Object value;
        List<String> fieldNames = ReflectionHelper.getClassFieldNames(dataSet);
        for (String fieldName : fieldNames) {
            value = ReflectionHelper.getFieldValue(dataSet, fieldName);
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
