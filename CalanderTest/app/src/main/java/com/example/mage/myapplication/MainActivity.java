package com.example.mage.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CalanderUtils calendarUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarUtils=new CalanderUtils ();
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarUtils.addCalendarEvent(MainActivity.this,"haha","hahahahah事件",System.currentTimeMillis()+100);
            }
        });
        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarUtils.deleteCalendarEvent(MainActivity.this,"haha");
            }
        });
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarUtils.updateCalenderEvent(MainActivity.this,"haha","哈哈更新后的事件标题");
            }
        });
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = calendarUtils.queryCalenderEvent(MainActivity.this, "haha");
                System.out.println("haha:"+json);
            }
        });
        findViewById(R.id.btn_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = getDb();
                ContentValues values=new ContentValues();
                values.put("name","杜甫");
                values.put("age",12);
                db.insert("user",null,values);
            }
        });
        findViewById(R.id.btn_querydb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = getDb();
                Cursor cursor = db.query("user", null, null, null, null, null, null);
//                while (cursor.moveToNext()){
//                    String name = cursor.getString(cursor.getColumnIndex("name"));
//                    System.out.println("haha："+name);
//                }
                while (cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    System.out.println("haha"+name);
                    String  age = cursor.getString(cursor.getColumnIndex("age"));
                    System.out.println("haha"+age);

                }
            }
        });
    }

    public SQLiteDatabase getDb(){
        MySqliteOpenHelper dbHelper = new MySqliteOpenHelper(MainActivity.this, "mydb",null,1,"create table user(name varchar(20),age integer)");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db;
    }
}
