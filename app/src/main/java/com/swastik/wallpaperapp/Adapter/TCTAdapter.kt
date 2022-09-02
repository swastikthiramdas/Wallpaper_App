package com.swastik.wallpaperapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swastik.wallpaperapp.FinalimageActivity
import com.swastik.wallpaperapp.Model.TCTModel
import com.swastik.wallpaperapp.R
import kotlinx.android.synthetic.main.item_tct.view.*

class TCTAdapter(val requireContext: Context , private val tctlist: ArrayList<TCTModel>) : RecyclerView.Adapter<TCTAdapter.viewmodel>() {

    class viewmodel(view: View) : RecyclerView.ViewHolder(view) {

        val colore = view.tct_colore

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewmodel {

        val itemview = LayoutInflater.from(requireContext).inflate(R.layout.item_tct,parent,false)

        return viewmodel(itemview)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: viewmodel, position: Int) {

        val currentpossition = tctlist[position]

        holder.colore.setCardBackgroundColor(Color.parseColor(currentpossition.colore))

        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalimageActivity::class.java)
            intent.putExtra("uri",currentpossition.link)
            requireContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return tctlist.size
    }
}