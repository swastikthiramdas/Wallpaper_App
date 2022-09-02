package com.swastik.wallpaperapp

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_finalimage.*
import kotlinx.coroutines.*
import java.io.File
import java.net.URL
import java.io.IOException as IOException1


class FinalimageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalimage)


        val uri = intent.getStringExtra("uri")

        val urlImage =
            URL(uri)

        Glide.with(this).load(uri).into(final_image)

        download_wallpaper.setOnClickListener {

            saveImage(uri!!, "MyWallpapers")

        }




        set_wallpaper.setOnClickListener {

            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }

            GlobalScope.launch(Dispatchers.Main) {

                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())
            }

        }
    }


    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException1) {
            null
        }

    }

    fun saveImage(uri: String, filename: String) {


        try {
            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val imagelink = Uri.parse(uri)
            val path = "/Pictures/MyWallpapers"

            val requst = DownloadManager.Request(imagelink)
            requst.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setMimeType("image/jpeg")
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(filename)
                .setDestinationInExternalPublicDir(
                    path , "$filename.jpg"
                )
            downloadManager.enqueue(requst)
            Toast.makeText(this, "Image Save", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {

            Toast.makeText(this, "Image Not Save", Toast.LENGTH_SHORT).show()

        }

    }
}