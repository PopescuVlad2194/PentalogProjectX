package com.example.course9feature.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.course9feature.R
import com.example.course9feature.data.Album
import com.example.course9feature.databinding.AlbumsListItemBinding

class AlbumsAdapter(private var albums: MutableList<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    inner class AlbumsViewHolder(val binding: AlbumsListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val binding = AlbumsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val list = albums[position]
        holder.binding.let { element ->
            element.tvAlbumName.text = list.name
            element.tvAlbumYear.text = list.year.toString()
            checkFavoriteState(element, list)
            element.ivFavorite.setOnClickListener {
                removeAt(position)
            }
        }
    }

    private fun checkFavoriteState(
        element: AlbumsListItemBinding,
        currentItem: Album
    ) {
        element.ivFavorite.setImageResource(
            if (currentItem.favorite)
                R.drawable.ic_liked
            else R.drawable.ic_notliked
        )
    }

    override fun getItemCount(): Int = albums.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(list: MutableList<Album>) {
        albums = list
        notifyDataSetChanged()
    }

    private fun removeAt(position: Int) {
        albums.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, albums.size)
    }
}