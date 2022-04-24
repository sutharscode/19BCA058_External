package com.example.a19bca058_external

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewallrecyclestruct.view.*

class FruitAdapter (val context: Context, var arr:ArrayList<Fruit>)
    : RecyclerView.Adapter<FruitAdapter.ViewHolder>()

{

    class ViewHolder(var view: View): RecyclerView.ViewHolder(view)
    {
        fun bind(f:Fruit)
        {
            view.txtStructFId.setText(f.F_Id.toString())
            view.txtStructFName.setText(f.F_Name)
            view.txtStructFPrice.setText(f.F_Price.toString())
            view.txtStructFQty.setText(f.F_Qty.toString())

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.viewallrecyclestruct,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arr[position])
        holder.view.imgUpdate.setOnClickListener {
            if(context is ViewAllFruits)
            {
                context.Update(position)
            }
        }
        holder.view.imgDelete.setOnClickListener {
            if(context is ViewAllFruits)
            {
                context.Delete(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return arr.size
    }
}