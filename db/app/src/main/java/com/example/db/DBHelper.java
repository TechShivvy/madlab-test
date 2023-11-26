package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(id integer primary key autoincrement,name text, age integer, course text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists student");
        onCreate(db);
    }

    public String addUser(String name,Integer age,String course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();
        cv.put("name",name);
        cv.put("age",age);
        cv.put("course",course);

        long res= db.insert("student",null,cv);
//        db.close();
        return res==-1?"Unable to add user":"User Added";
    }

    public List<List<String>> readUsers(String condition) {
        SQLiteDatabase db = this.getWritableDatabase();

//        String query = "SELECT * from student";
        String query = "SELECT * FROM student WHERE " + condition;

        List<List<String>> userList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                List<String> userData = new ArrayList<>();
                userData.add(cursor.getString(cursor.getColumnIndex("name")));
                userData.add(String.valueOf(cursor.getInt(2)));
                userData.add(cursor.getString(3));
//                userData.addAll(stringToList(cursor.getString(4)));
                userList.add(userData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }
    public String updateUser(String oldName, String newName, int newAge, String newCourse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", newName);
        cv.put("age", newAge);
        cv.put("course", newCourse);

        int rowsAffected = db.update("student", cv, "name=?", new String[]{oldName});
        return rowsAffected<=0?"Not Updated":"Updated";
    }

    public String deleteUser(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("student", "name=?", new String[]{name});
        return rowsAffected<=0?"Not Deleted":"Deleted";
    }

    // Helper method to convert List<String> to a single string
//    private String listToString(List<String> list) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String item : list) {
//            stringBuilder.append(item).append(",");
//        }
//        return stringBuilder.toString();
//    }
//
//    // Helper method to convert a comma-separated string to List<String>
//    private List<String> stringToList(String input) {
//        String[] array = input.split(",");
//        List<String> list = new ArrayList<>();
//        for (String item : array) {
//            list.add(item);
//        }
//        return list;
//    }
}
