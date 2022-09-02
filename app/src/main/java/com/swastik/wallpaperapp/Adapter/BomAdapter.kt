package com.swastik.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swastik.wallpaperapp.FinalimageActivity
import com.swastik.wallpaperapp.Model.BomModel
import com.swastik.wallpaperapp.R


class BomAdapter(val requiredcontext: Context, private val list: ArrayList<BomModel>): RecyclerView.Adapter<BomAdapter.viewholder>() {
    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val image = view.findViewById<ImageView>(R.id.bom_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val itemview = LayoutInflater.from(requiredcontext).inflate(R.layout.item_bom,parent,false)

        return viewholder(itemview)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val currentitem = list[position]

        Glide.with(requiredcontext).load(currentitem.link).into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(requiredcontext,FinalimageActivity::class.java)
            intent.putExtra("uri",currentitem.link)
            requiredcontext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

        return list.size
    }
}