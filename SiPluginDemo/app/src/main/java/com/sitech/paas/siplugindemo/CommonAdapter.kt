package com.example.sitechapm

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sitech.paas.siplugindemo.R

class CommonAdapter(var context: Context,var data:MutableList<String>) : RecyclerView.Adapter<CommonHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonHolder {
        val view = View.inflate(context, R.layout.layout_item, null)
        return CommonHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CommonHolder, position: Int) {
        holder.tv.setText(data.get(position))
    }

}