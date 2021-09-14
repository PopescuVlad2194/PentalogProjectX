package com.example.course9feature.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.course9feature.databinding.AlbumsListItemBinding
import com.example.course9feature.model.Album

class AlbumsAdapter(var albums: List<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    inner class AlbumsViewHolder(val binding: AlbumsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val binding = AlbumsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val currentItem = albums[position]
        holder.binding.let { element ->
            element.tvAlbumName.text = currentItem.strAlbum
            element.tvAlbumYear.text = currentItem.intYearReleased
            element.tvArtistName.text = currentItem.artist
        }
    }

    override fun getItemCount(): Int = albums.size

    fun update(list: List<Album>) {
        albums = list
        notifyDataSetChanged()
    }
}