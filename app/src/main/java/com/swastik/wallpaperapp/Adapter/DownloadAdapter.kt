package com.swastik.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swastik.wallpaperapp.FinalimageActivity
import com.swastik.wallpaperapp.Model.BomModel
import com.swastik.wallpaperapp.R
import kotlinx.android.synthetic.main.item_wallpaper.view.*


class DownloadAdapter(val requiredcontext: Context, private val list: ArrayList<String>): RecyclerView.Adapter<DownloadAdapter.viewholder>() {
    class viewholder(view: View) : RecyclerView.ViewHolder(view) {

        val image = view.item_image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val itemview = LayoutInflater.from(requiredcontext).inflate(R.layout.item_wallpaper,parent,false)

        return viewholder(itemview)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val currentitem = list[position]

        Glide.with(requiredcontext).load(currentitem).into(holder.image)

    }

    override fun getItemCount(): Int {

        return list.size
    }
}