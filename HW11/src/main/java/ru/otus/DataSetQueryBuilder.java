package ru.otus;

import ru.otus.DataSets.DataSet;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DataSetQueryBuilder {


    StringBuilder query;

    public String getInsertQuery(DataSet dataSet) {
        if (dataSet != null) {
            query = new StringBuilder();
            List<Field> fields = ReflectionHelper.getClassFields(dataSet);
            List<String> fieldNames = fields.stream()
                    .map(Field::getName)
                    .collect(Collectors.toList());
            String placeHolders = String.join(", ", Collections.nCopies(fields.size(), "?"));
            return query
                    .append("INSERT INTO user (")
                    .append(getStringFromList(fieldNames))
                    .append(") VALUES (")
                    .append(placeHolders)
                    .append(");")
                    .toString();
        } else {
            System.err.println("Input parameter 'dataSet' is null!");
            return "";
        }
    }

    public String getInsertQuery(DataSet dataSet, List<String> dataSetFields) {
        if (dataSet != null) {
            query = new StringBuilder();
            String placeHolders = String.join(", ", Collections.nCopies(dataSetFields.size(), "?"));
            return query
                    .append("INSERT INTO user (")
                    .append(getStringFromList(dataSetFields))
                    .append(") VALUES (")
                    .append(placeHolders)
                    .append(");")
                    .toString();
        } else {
            System.err.println("Input parameter 'dataSet' is null!");
            return "";
        }
    }


    private String getStringFromList(List<String> list) {
        return list.toString()
                .replace("[", "")
                .replace("]", "");
    }
}
