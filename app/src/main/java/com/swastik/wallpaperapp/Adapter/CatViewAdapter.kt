package com.swastik.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swastik.wallpaperapp.CatActivity
import com.swastik.wallpaperapp.Model.CatModel
import com.swastik.wallpaperapp.R
import kotlinx.android.synthetic.main.item_cat.view.*

class CatViewAdapter(val requireContext: Context, private val catlist: ArrayList<CatModel>) : RecyclerView.Adapter<CatViewAdapter.mviewholder>() {

    class mviewholder(view: View) : RecyclerView.ViewHolder(view) {

        val image = view.Cat_image
        val text = view.Cat_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mviewholder {

        val itemview = LayoutInflater.from(requireContext).inflate(R.layout.item_cat,parent,false)

        return mviewholder(itemview)
    }

    override fun onBindViewHolder(holder: mviewholder, position: Int) {

        val currentpossition = catlist[position]

        Glide.with(requireContext).load(currentpossition.link).into(holder.image)
        holder.text.text = currentpossition.name

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext,CatActivity::class.java)
            intent.putExtra("name",currentpossition.name)
            intent.putExtra("size",catlist.size)
            intent.putExtra("uid",currentpossition.id)
            requireContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

        return catlist.size

    }
}