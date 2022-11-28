package com.team.vinylos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.vinylos.databinding.AlbumItemBinding
import androidx.databinding.DataBindingUtil
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.team.vinylos.R
import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector

class CollectorDetailAdapter() : RecyclerView.Adapter<CollectorDetailAdapter.CollectorDetailViewHolder>() {

    var collector: Collector? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var albums: List<Album> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorDetailViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorDetailViewHolder.LAYOUT,
            parent,
            false
        )

        return CollectorDetailViewHolder(withDataBinding)
    }


    override fun onBindViewHolder(holder: CollectorDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }

        holder.bind(albums[position])
        holder.viewDataBinding.root.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class CollectorDetailViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }

        fun bind(album: Album) {
            Glide.with(itemView)
                .load(album.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image)
                )
                .into(viewDataBinding.cover)
        }

    }

}