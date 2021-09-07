package com.example.course9feature.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.course9feature.R
import com.example.course9feature.adapters.ArtistsAdapter
import com.example.course9feature.databinding.FragmentArtistsBinding
import com.example.course9feature.ui.MainActivity
import com.example.course9feature.ui.MainViewModel
import com.example.course9feature.model.Artist
import com.google.android.material.snackbar.Snackbar

class ArtistsFragment : Fragment(R.layout.fragment_artists) {
    private lateinit var binding: FragmentArtistsBinding
    private lateinit var artistsAdapter: ArtistsAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var favoriteArtists: List<Artist>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtistsBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupArtistsAdapter()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val artist = artistsAdapter.artists[position]
                viewModel.deleteArtist(artist)
                Snackbar.make(
                    view,
                    resources.getString(R.string.delete_artist_msg),
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction("Undo") {
                        viewModel.saveArtist(artist)
                    }.show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvArtists)
        }

        viewModel.getSavedArtists().observe(viewLifecycleOwner, { artists ->
            favoriteArtists = artists
            artistsAdapter.update(favoriteArtists)
        })
    }

    private fun setupArtistsAdapter() {
        favoriteArtists = emptyList()
        artistsAdapter = ArtistsAdapter(favoriteArtists)

        binding.rvArtists.apply {
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = artistsAdapter
        }
        artistsAdapter.update(favoriteArtists)
    }
}