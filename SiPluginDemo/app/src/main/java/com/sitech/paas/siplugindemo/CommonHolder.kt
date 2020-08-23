package com.example.sitechapm

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.sitech.paas.siplugindemo.R

class CommonHolder: RecyclerView.ViewHolder {
    var tv:TextView
    constructor(view: View) : super(view) {
         tv = view.findViewById<TextView>(R.id.tv_item)
    }
}