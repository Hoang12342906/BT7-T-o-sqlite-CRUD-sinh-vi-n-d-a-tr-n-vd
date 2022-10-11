package com.example.sinhvien.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sinhvien.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private SQLiteDatabase db;

    public StudentDao(Context context) {
        DBHelper helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public List<Student> get(String sql, String ...selectArgs){
        List<Student> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            Student stu = new Student();
            stu.setId(cursor.getString(cursor.getColumnIndex("id")));
            stu.setName(cursor.getString(cursor.getColumnIndex("name")));
            stu.setSalary(cursor.getFloat(cursor.getColumnIndex("salary")));

            list.add(stu);
        }
        return list;
    }
    public List<Student> getAll(){
        String sql = "SELECT * FROM sinhvien";

        return get(sql);
    }
    public Student getById(String id){
        String sql = "SELECT * FROM sinhvien WHERE id=?";
        List<Student> list = get(sql,id);
        return list.get(0);
    }
    public long insert(Student stu){
        ContentValues values = new ContentValues();
        values.put("id",stu.getId());
        values.put("name", stu.getName());
        values.put("salary", stu.getSalary());

        return db.insert("sinhvien",null, values);
    }

    public long update(Student stu){
        ContentValues values = new ContentValues();
        values.put("name", stu.getName());
        values.put("salary", stu.getSalary());

        return db.update("sinhvien", values, "id=?", new String[]{stu.getId()});
    }
    public int  delete(String id){
        return db.delete("sinhvien","id=?", new String[]{id});
    }
}
