package com.example.course9feature.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.course9feature.databinding.ArtistsListItemBinding
import com.example.course9feature.model.Artist

class ArtistsAdapter(var artists: List<Artist>) :
    RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder>() {

    inner class ArtistsViewHolder(val binding: ArtistsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsViewHolder {
        val binding = ArtistsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        val currentItem = artists[position]
        holder.binding.let { element ->
            element.tvArtistName.text = currentItem.strArtist
            element.tvArtistStyle.text = currentItem.strStyle
            element.tvArtistCountry.text = currentItem.strCountry
            Glide.with(element.ivArtistThumbnail.context)
                .load(currentItem.strArtistThumb)
                .centerCrop()
                .into(element.ivArtistThumbnail)
        }
    }

    override fun getItemCount(): Int = artists.size

    fun update(list: List<Artist>) {
        artists = list
        notifyDataSetChanged()
    }
}