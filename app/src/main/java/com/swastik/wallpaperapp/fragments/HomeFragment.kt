package com.swastik.wallpaperapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.swastik.wallpaperapp.Adapter.BomAdapter
import com.swastik.wallpaperapp.Adapter.CatViewAdapter
import com.swastik.wallpaperapp.Adapter.TCTAdapter
import com.swastik.wallpaperapp.Model.BomModel
import com.swastik.wallpaperapp.Model.CatModel
import com.swastik.wallpaperapp.Model.TCTModel
import com.swastik.wallpaperapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bestofthemonth()
        thecoloreton()
        getcat()

        return inflater.inflate(R.layout.fragment_home, container, false)

    }


    private fun thecoloreton() {
        db = FirebaseFirestore.getInstance()
        db.collection("thecoloreton").addSnapshotListener { value, error ->

            if (value != null) {

                val tctlist = ArrayList<TCTModel>()
                val data = value.toObjects(TCTModel::class.java)


                tctlist.addAll(data)

                tct.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                tct.adapter = TCTAdapter(requireContext(), tctlist)
            } else {

                Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bestofthemonth() {

        db = FirebaseFirestore.getInstance()
        db.collection("bestofthemonth").addSnapshotListener { value, error ->

            if (value != null) {

                val bestofthemonthlist = arrayListOf<BomModel>()
                val data = value.toObjects(BomModel::class.java)

                bestofthemonthlist.addAll(data)

                rcv_bom.adapter = BomAdapter(requireContext(), bestofthemonthlist)
                rcv_bom.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            } else {
                Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getcat() {
        db = FirebaseFirestore.getInstance()
        db.collection("categories").addSnapshotListener { value, error ->

            if (value != null) {

                val catlist = ArrayList<CatModel>()
                val data = value.toObjects(CatModel::class.java)


                catlist.addAll(data)

                cat.layoutManager = GridLayoutManager(requireContext(), 2)

                cat.adapter = CatViewAdapter(requireContext(), catlist)


            } else {

                Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        }

    }

}