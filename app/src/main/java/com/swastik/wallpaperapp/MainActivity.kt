package com.swastik.wallpaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.swastik.wallpaperapp.fragments.DownloadFragment
import com.swastik.wallpaperapp.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ic_home = findViewById<FrameLayout>(R.id.ic_home)
        val ic_download = findViewById<FrameLayout>(R.id.ic_download)

        fragmentreplace(HomeFragment())
        ic_home.setOnClickListener {

            fragmentreplace(HomeFragment())
        }
        ic_download.setOnClickListener {

            fragmentreplace(DownloadFragment())
        }

    }
    fun fragmentreplace(fragment: Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()

    }
}