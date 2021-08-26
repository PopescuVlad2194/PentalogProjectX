package com.example.course9feature.navigation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.course9feature.R
import com.example.course9feature.databinding.FragmentArtistsBinding

class ArtistsFragment : Fragment(R.layout.fragment_artists) {
    private lateinit var binding: FragmentArtistsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtistsBinding.bind(view)

    }
}