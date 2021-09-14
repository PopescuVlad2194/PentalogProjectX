package com.example.course9feature.ui.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.appcompat.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.course9feature.R
import com.example.course9feature.databinding.FragmentSearchBinding
import com.example.course9feature.model.Artist
import com.example.course9feature.model.Discography
import com.example.course9feature.ui.MainActivity
import com.example.course9feature.ui.MainViewModel
import com.example.course9feature.util.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var discography: Discography
    private lateinit var currentArtist: Artist
    private var searchKey: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotEmpty() == true) {
                    searchKey = checkInput(query)
                    viewModel.getResult(searchKey)
                    binding.searchView.setQuery("", false)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.favoriteCb.setOnClickListener {
            currentArtist.liked = binding.favoriteCb.isChecked
            currentArtist.let { artist -> viewModel.saveArtist(artist) }
            Snackbar.make(view, resources.getString(R.string.add_artist_msg), Snackbar.LENGTH_SHORT)
                .show()
        }

        binding.btnArtistDiscography.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("discography", discography)
                putSerializable("thumbnail", currentArtist.strArtistWideThumb)
                putSerializable("artist_name", currentArtist.strArtist)
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_discographyFragment,
                bundle
            )
        }

        binding.artistBiography.movementMethod = ScrollingMovementMethod()

        viewModel.discography.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { discographyResponse ->
                        discography = discographyResponse
                    }
                }
            }
        })

        viewModel.artists.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { artistResponse ->
                        showArtistDetails()
                        for (element in artistResponse.artists) {
                            currentArtist = element
                            binding.artistBiography.text = element.strBiographyEN
                            Glide.with(this@SearchFragment)
                                .load(element.strArtistWideThumb)
                                .centerCrop()
                                .into(binding.artistThumbnail)
                            Glide.with(this)
                                .load(element.strArtistLogo)
                                .centerCrop()
                                .into(binding.ivArtistLogo)
                            binding.artistGender.text = element.strGender
                            binding.artistStyle.text = element.strStyle
                            viewModel.getDiscography(element.strArtist)
                            viewModel.checkForArtist(element.strArtist)
                        }
                    }
                }
                is Resource.Error -> {
                    response.message?.let {
                        hideArtistDetails()
                        Log.d("Response", it)
                    }
                }
            }
        })

        viewModel.isFavorite.observe(viewLifecycleOwner, { artistFound ->
            if (artistFound != null) {
                showArtistDetails()
                currentArtist = artistFound
                binding.artistBiography.text = artistFound.strBiographyEN
                Glide.with(this)
                    .load(artistFound.strArtistWideThumb)
                    .centerCrop()
                    .into(binding.artistThumbnail)
                Glide.with(this)
                    .load(artistFound.strArtistLogo)
                    .centerCrop()
                    .into(binding.ivArtistLogo)
                binding.favoriteCb.toggle()
                binding.artistGender.text = artistFound.strGender
                binding.artistStyle.text = artistFound.strStyle
                viewModel.getDiscography(artistFound.strArtist)
            }
        })
    }

    private fun checkInput(query: String): String {
        return query[0].uppercaseChar() + query.substring(1)
    }

    private fun showArtistDetails() {
        binding.searchResultError.visibility = View.INVISIBLE
        binding.ivDefaultBackground.visibility = View.INVISIBLE
        binding.artistContainer.visibility = View.VISIBLE

    }

    private fun hideArtistDetails() {
        binding.searchResultError.visibility = View.VISIBLE
        binding.ivDefaultBackground.visibility = View.VISIBLE
        binding.artistContainer.visibility = View.INVISIBLE
    }
}