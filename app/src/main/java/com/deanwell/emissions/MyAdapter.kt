package com.deanwell.emissions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deanwell.testlist.model.ReqresItem
import kotlinx.android.synthetic.main.item_view.view.*
import java.util.*

class MyAdapter(private var dataList: MutableList<ReqresItem>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
    val dataList2: MutableList<ReqresItem> = mutableListOf()

    private lateinit var context: Context
    var onItemClick: ((ReqresItem) -> Unit)? = null
    var onButtonClick: ((ReqresItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        dataList2.addAll(dataList)
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = dataList[position]

        val AirplaneTextView: TextView = holder.itemView.findViewById(R.id.flight_airplane)
        val FlightTextView: TextView = holder.itemView.findViewById(R.id.flight_name)
        //val FromToTextView: TextView = holder.itemView.findViewById(R.id.flight_from_to)

        AirplaneTextView.text = "${data.airplane} \n\n${data.date}\n${data.from} --> ${data.to}"
        FlightTextView.text = "${data.flight}"
        //FromToTextView.text = ""

    }

    override fun getItemCount(): Int = dataList.size

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition])
            }
            itemView.more_info.setOnClickListener {
                onButtonClick?.invoke(dataList[adapterPosition])
            }
        }
    }

}