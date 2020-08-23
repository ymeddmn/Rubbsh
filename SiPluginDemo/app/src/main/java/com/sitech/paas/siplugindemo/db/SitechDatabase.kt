package com.example.sitechapm.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class SitechDatabase(context: Context) : ManagedSQLiteOpenHelper(context, "sitech",
        null, 1) {
    companion object {
        private var instance: SitechDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SitechDatabase {
            if (instance == null) {
                instance = SitechDatabase(context)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable("table", true,
                "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                "index" to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 此处对表结构进行更新
    }
}

internal val Context.sitechDatabase: SitechDatabase
    get() = SitechDatabase.getInstance(this)