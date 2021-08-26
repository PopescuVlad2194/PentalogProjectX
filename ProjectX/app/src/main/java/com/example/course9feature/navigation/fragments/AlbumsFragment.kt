package com.example.course9feature.navigation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.course9feature.R
import com.example.course9feature.databinding.FragmentAlbumsBinding

class AlbumsFragment : Fragment(R.layout.fragment_albums) {
    private lateinit var binding: FragmentAlbumsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumsBinding.bind(view)
    }
}