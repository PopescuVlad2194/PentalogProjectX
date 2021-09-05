package com.example.course9feature.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.course9feature.databinding.DiscographyListItemBinding
import com.example.course9feature.model.Album

class DiscographyAdapter(private var albums: MutableList<Album>) :
    RecyclerView.Adapter<DiscographyAdapter.DiscographyViewHolder>() {

    inner class DiscographyViewHolder(val binding: DiscographyListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscographyViewHolder {
        val binding = DiscographyListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscographyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscographyViewHolder, position: Int) {
        val list = albums[position]
        holder.binding.let { element ->
            element.albumName.text = list.strAlbum
            element.albumYear.text = list.intYearReleased
        }
    }

    override fun getItemCount(): Int = albums.size
}