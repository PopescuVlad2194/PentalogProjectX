package com.example.course9feature.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.course9feature.R
import com.example.course9feature.adapters.ArtistsAdapter
import com.example.course9feature.data.Artist
import com.example.course9feature.data.ArtistsDataSet
import com.example.course9feature.databinding.FragmentArtistsBinding
import com.example.course9feature.ui.MainActivity
import com.example.course9feature.ui.MainViewModel
import com.google.gson.Gson

class ArtistsFragment : Fragment(R.layout.fragment_artists) {
    private lateinit var binding: FragmentArtistsBinding
    private lateinit var artistsAdapter: ArtistsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtistsBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupArtistsAdapter()
    }

    private fun setupArtistsAdapter() {
        val dataSet = arrayListOf<Artist>()
        artistsAdapter = ArtistsAdapter(artists = dataSet)

        binding.rvArtists.apply {
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = artistsAdapter
        }

        dataSet.addAll(generateData())
        artistsAdapter.update(dataSet)
    }

    private fun generateData(): List<Artist> {
        val jsonString = getString(R.string.artists_data)
        val jsonDataObject = Gson().fromJson(jsonString, ArtistsDataSet::class.java)
        return jsonDataObject.artists
    }
}