package com.example.mage.myapplication;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * author  :mayong
 * function:
 * date    :2019-12-03
 **/
public class MySqliteOpenHelper extends SQLiteOpenHelper {
    private  String dbSql;

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String dbSql) {
        super(context, name, factory, version);
        this.dbSql=dbSql;
    }
    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public MySqliteOpenHelper(Context context, String name, int version, SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
