package com.example.sitechapm.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sitechapm.db.Data
import com.example.sitechapm.db.sitechDatabase
import com.sitech.paas.siplugindemo.CrashApplication
import com.sitech.paas.siplugindemo.R
import kotlinx.android.synthetic.main.activity_database.*
import org.jetbrains.anko.db.*
import java.util.ArrayList

class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        initListener()
    }

    private fun initListener() {
        btn_add.setOnClickListener {
            for (i in 0..100) {
                insertAnumber(this, i)
            }
        }
        btn_query.setOnClickListener {
            var data = queryData()
            for (d in data){
                println(d.index)
            }
        }
    }

    private fun queryData(): ArrayList<Data> {
//       var data: ArrayList<Data> = ArrayList()
        var data: ArrayList<Data> = ArrayList()
        CrashApplication.getConstex().sitechDatabase.use {
            select("sitech")
                    .limit(10)
                    .column("index")
                    .exec {
                         parseList(object : RowParser<Data> {
                            override fun parseRow(columns: Array<Any?>): Data {
                                val any: Int = columns[1] as Int
                                var d = Data(any)
                                data.add(d)
                                return d
                            }
                        })
                    }
        }
        return data

    }

    private fun insertAnumber(context: Context, index: Int) {
        context.sitechDatabase.use {
            pageSize = 10
            transaction {
                insert("sitech", "index" to index)
            }
        }
    }

}
