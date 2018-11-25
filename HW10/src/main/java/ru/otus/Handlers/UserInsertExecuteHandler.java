package ru.otus.Handlers;

import ru.otus.DataSet;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInsertExecuteHandler implements ExecuteHandler {

    private DataSet dataSet;
    StringBuilder query = new StringBuilder();
    List<String> columnList = new ArrayList<>();
    List<String> valueList = new ArrayList<>();

    public UserInsertExecuteHandler(DataSet dataSet){
      this.dataSet = dataSet;
    }


    public String getQuery() throws IllegalAccessException {
     List<Field> fieldList = Arrays.asList(dataSet.getClass().getDeclaredFields());
     for (Field field: fieldList){
         field.setAccessible(true);
         if(field.get(dataSet) instanceof String){
             columnList.add(field.getName());
             valueList.add("'" + field.get(dataSet) + "'");
         } else {
             columnList.add(field.getName());
             valueList.add(String.valueOf(field.get(dataSet)));
         }
     }
     return query
             .append("INSERT INTO hw10.user (")
             .append(getStringFromList(columnList))
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
