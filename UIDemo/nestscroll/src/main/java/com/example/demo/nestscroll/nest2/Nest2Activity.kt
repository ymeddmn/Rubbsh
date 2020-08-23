package com.example.demo.nestscroll.nest2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.demo.nestscroll.R
import kotlinx.android.synthetic.main.activity_nest2.*

class Nest2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nest2)
        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = IAdapter(this)
    }

    class IHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    class IAdapter(var nest2Activity: Nest2Activity) : RecyclerView.Adapter<IHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): IHolder {
            var view = Button(nest2Activity)
            view.width = nest2Activity.resources.displayMetrics.widthPixels
            var holder = IHolder(view)
            return holder
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: IHolder?, position: Int) {
            var btn: Button = holder?.itemView as Button
            btn.text = position.toString()
        }

    }
}
