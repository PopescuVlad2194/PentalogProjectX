package com.example.course9feature.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.course9feature.R
import com.example.course9feature.data.Artist
import com.example.course9feature.databinding.ArtistsListItemBinding

class ArtistsAdapter(private var artists: List<Artist>) :
    RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder>() {

    inner class ArtistsViewHolder(val binding: ArtistsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsViewHolder {
        val binding = ArtistsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        val list = artists[position]
        holder.binding.let { element ->
            element.tvArtistName.text = list.name
            element.tvArtistGenre.text = list.genre
            checkLikeState(element, list)
            element.ivLiked.setOnClickListener {
                list.liked = !list.liked
                checkLikeState(element, list)
            }
            Glide.with(element.ivArtistThumbnail.context)
                .load(list.thumbnail)
                .centerCrop()
                .into(element.ivArtistThumbnail)
        }
    }

    override fun getItemCount(): Int = artists.size


    fun update(list: List<Artist>) {
        artists = list
        notifyDataSetChanged()
    }

    private fun checkLikeState(
        holder: ArtistsListItemBinding,
        element: Artist
    ) {
        holder.ivLiked.setImageResource(
            if (element.liked)
                R.drawable.ic_liked
            else R.drawable.ic_notliked
        )
    }
}