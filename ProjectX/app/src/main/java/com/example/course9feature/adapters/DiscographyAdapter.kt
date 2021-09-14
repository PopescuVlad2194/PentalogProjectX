package com.example.course9feature.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.course9feature.databinding.DiscographyListItemBinding
import com.example.course9feature.model.Album

class DiscographyAdapter(private var albums: List<Album>) :
    RecyclerView.Adapter<DiscographyAdapter.DiscographyViewHolder>() {

    var onClick: ((Album) -> Unit)? = null

    inner class DiscographyViewHolder(val binding: DiscographyListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscographyViewHolder {
        val binding = DiscographyListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscographyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscographyViewHolder, position: Int) {
        val currentItem = albums[position]
        holder.binding.let { element ->
            element.albumName.text = currentItem.strAlbum
            element.albumYear.text = currentItem.intYearReleased
            element.albumFavorite.setOnClickListener {
                currentItem.isLiked = true
                onClick?.invoke(currentItem)
            }
        }
    }

    override fun getItemCount(): Int = albums.size

}