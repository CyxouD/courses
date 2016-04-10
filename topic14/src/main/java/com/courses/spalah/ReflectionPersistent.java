package com.courses.spalah;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Dima on 06.04.2016.
 */
public class ReflectionPersistent {
    boolean[][] b = {{true,false}};


    public void store(String tableName, Object obj){
        Class<?> tClass = obj.getClass();
        Field[] tClassFields = tClass.getDeclaredFields();
        ArrayList<String> fieldsName = null;
        ArrayList<String> fieldsTypes = null;
        ArrayList fieldValues = new ArrayList();
        String fieldName;
        for (Field f : tClassFields){
            fieldsTypes.add(f.getType().getSimpleName());
            fieldName = f.getName();
            fieldsName.add(fieldName);
            try {
                f.setAccessible(true);
                fieldValues.add(f.get(fieldName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Class<?> type = fieldsTypes.get(0).getClass();
    }


    public static void main(String[] args){
        ReflectionPersistent reflectionPersistent = new ReflectionPersistent();
        Class<?> tClass = reflectionPersistent.b.getClass();
        System.out.println(tClass.getName());
        System.out.println(tClass.getSimpleName());
        System.out.println(tClass.getCanonicalName());
        System.out.println(tClass.getTypeName());
    }
}
