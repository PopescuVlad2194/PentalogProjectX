package com.example.course9feature.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.course9feature.R
import com.example.course9feature.adapters.DiscographyAdapter
import com.example.course9feature.databinding.FragmentDiscographyBinding
import com.example.course9feature.model.Album
import com.example.course9feature.ui.MainActivity
import com.example.course9feature.ui.MainViewModel

class DiscographyFragment : Fragment(R.layout.fragment_discography) {
    private lateinit var binding: FragmentDiscographyBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var albums: MutableList<Album>
    private lateinit var discographyAdapter: DiscographyAdapter
    val args: DiscographyFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiscographyBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        albums = mutableListOf()
        albums.addAll(args.discography.album)

        Glide.with(this)
            .load(args.thumbnail)
            .centerCrop()
            .into(binding.artistThumbnail)
        setupAlbumsAdapter()
    }

    private fun setupAlbumsAdapter() {
        discographyAdapter = DiscographyAdapter(albums)

        binding.rvDiscography.apply {
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = discographyAdapter
        }
    }
}