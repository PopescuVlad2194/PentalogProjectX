package com.example.course9feature.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.course9feature.R
import com.example.course9feature.adapters.AlbumsAdapter
import com.example.course9feature.databinding.FragmentAlbumsBinding
import com.example.course9feature.model.Album
import com.example.course9feature.ui.MainActivity
import com.example.course9feature.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar

class AlbumsFragment : Fragment(R.layout.fragment_albums) {
    private lateinit var binding: FragmentAlbumsBinding
    private lateinit var albumsAdapter: AlbumsAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var favoriteAlbums: List<Album>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumsBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        setupDiscographyAdapter()

        viewModel.getSavedAlbums().observe(viewLifecycleOwner, { albums ->
            favoriteAlbums = albums
            albumsAdapter.update(favoriteAlbums)
        })

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
                val album = albumsAdapter.albums[position]
                viewModel.deleteAlbum(album)

                Snackbar.make(
                    view,
                    resources.getString(R.string.delete_artist_msg),
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction("Undo") {
                        viewModel.saveAlbum(album)
                    }.show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvAlbums)
        }
    }

    private fun setupDiscographyAdapter() {
        favoriteAlbums = mutableListOf()
        albumsAdapter = AlbumsAdapter(favoriteAlbums)

        binding.rvAlbums.apply {
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = albumsAdapter
        }
    }
}