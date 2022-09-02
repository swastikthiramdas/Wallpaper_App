package com.swastik.wallpaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.swastik.wallpaperapp.Adapter.CatAdapter
import com.swastik.wallpaperapp.Model.BomModel
import kotlinx.android.synthetic.main.activity_cat.*

class CatActivity : AppCompatActivity() {

    private lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)

        db = FirebaseFirestore.getInstance()

        val image = intent.getStringExtra("uid")
        val name = intent.getStringExtra("name")
        val size = intent.getStringExtra("size")

        db.collection("categories").document(image!!).collection("wallpaper").addSnapshotListener{ value , error ->

            if (value != null) {

                val catlist = ArrayList<BomModel>()
                val data = value.toObjects(BomModel::class.java)

                catlist.addAll(data)
                tittle_cat.setText(name)
                count_cat.text = "${catlist.size} Wallpaper Available"
                recyclerview_cat.layoutManager = GridLayoutManager(this,2)
                recyclerview_cat.adapter = CatAdapter(this,catlist)


            } else {

                Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }
        }




    }
}