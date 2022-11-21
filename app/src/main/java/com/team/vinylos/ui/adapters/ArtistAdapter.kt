package com.team.vinylos.ui.adapters

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.vinylos.databinding.ArtistItemBinding
import androidx.databinding.DataBindingUtil
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.team.vinylos.R
import com.team.vinylos.models.Artist
import com.team.vinylos.ui.ArtistDetailsActivity

class ArtistAdapter() : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {



    var artists: List<Artist> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false
        )
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artists[position]
        }
        holder.bind(artists[position])
        holder.viewDataBinding.root.setOnClickListener {
            val Id = artists[position]
            Log.d("Enviando Id:", Id.id.toString())
            val activity = holder.itemView.context
            val i = Intent(activity, ArtistDetailsActivity::class.java)
            i.putExtra("id", Id.id.toString())
            i.putExtra("name", Id.name)
            activity.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return artists.size
    }


    class ArtistViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_item
        }

        fun bind(artist: Artist) {
            Glide.with(itemView)
                .load(artist.image.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image)
                )
                .into(viewDataBinding.image)
        }
    }

}