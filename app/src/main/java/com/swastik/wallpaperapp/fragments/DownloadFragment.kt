package com.swastik.wallpaperapp.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.swastik.wallpaperapp.Adapter.DownloadAdapter
import com.swastik.wallpaperapp.databinding.FragmentDownloadBinding
import java.io.File


class DownloadFragment : Fragment() {


    lateinit var binding: FragmentDownloadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDownloadBinding.inflate(layoutInflater, container, false)
        val allfiles: Array<File>
        var imagelist = ArrayList<String>()


        val targetpath =
            Environment.getExternalStorageDirectory().absolutePath + "/Pictures/Amoled Wallpaper"

        val Targetfile = File(targetpath)
        allfiles = Targetfile.listFiles()!!

        for (data in allfiles) {

            imagelist.add(data.absolutePath)
        }

        for (i in imagelist) {

            Log.i("Tag", "OnCreateView " + i)
        }

        binding.download.adapter = DownloadAdapter(requireContext(), imagelist)
        binding.download.layoutManager = GridLayoutManager(requireContext(), 2)



        return binding.root

    }

}