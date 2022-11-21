package com.team.vinylos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.vinylos.databinding.ArtistItemBinding
import androidx.databinding.DataBindingUtil
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.team.vinylos.R
import com.team.vinylos.databinding.ArtistDetailsBinding
import com.team.vinylos.models.Artist

class ArtistDetailAdapter() : RecyclerView.Adapter<ArtistDetailAdapter.ArtistDetailViewHolder>() {

    var artists: Artist = Artist()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistDetailViewHolder {
        val withDataBinding: ArtistDetailsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistDetailViewHolder.LAYOUT,
            parent,
            false
        )
        return ArtistDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistDetailViewHolder, position: Int) {
       holder.viewDataBinding.also {
            it.artist = artists
        }
        holder.bind(artists)
        holder.viewDataBinding.root.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return 1
    }


    class ArtistDetailViewHolder(val viewDataBinding: ArtistDetailsBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_details
        }

        fun bind(artist: Artist) {
            Glide.with(itemView)
                .load(artist.image.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)
                )
                .into(viewDataBinding.imageArtist)
        }
    }

}