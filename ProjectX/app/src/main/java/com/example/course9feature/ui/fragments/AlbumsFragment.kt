package com.example.course9feature.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.course9feature.R
import com.example.course9feature.adapters.AlbumsAdapter
import com.example.course9feature.data.Album
import com.example.course9feature.data.AlbumsDataSet
import com.example.course9feature.databinding.FragmentAlbumsBinding
import com.example.course9feature.ui.MainActivity
import com.example.course9feature.ui.MainViewModel
import com.google.gson.Gson

class AlbumsFragment : Fragment(R.layout.fragment_albums) {
    private lateinit var binding: FragmentAlbumsBinding
    private lateinit var albumsAdapter: AlbumsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumsBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupAlbumsAdapter()
    }

    private fun setupAlbumsAdapter() {
        val dataSet = mutableListOf<Album>()
        albumsAdapter = AlbumsAdapter(albums = dataSet)

        binding.rvAlbums.apply {
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = albumsAdapter
        }

        dataSet.addAll(generateData())
        albumsAdapter.update(dataSet)
    }

    private fun generateData(): MutableList<Album> {
        val jsonString = getString(R.string.albums_data)
        val jsonDataObject = Gson().fromJson(jsonString, AlbumsDataSet::class.java)
        return jsonDataObject.albums
    }
}