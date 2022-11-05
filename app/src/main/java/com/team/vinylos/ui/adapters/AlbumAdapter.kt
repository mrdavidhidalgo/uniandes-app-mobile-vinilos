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
import com.bumptech.glide.request.RequestOptions
import com.team.vinylos.R
import com.team.vinylos.models.Album

class AlbumAdapter(albums:List<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){

    var albums :List<Album> = albums
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.bind(albums[position])
        holder.viewDataBinding.root.setOnClickListener {
            //val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
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
                        .placeholder(R.drawable.ic_launcher_background)

                        .error(R.drawable.ic_launcher_foreground))
                .into(viewDataBinding.albumCover)
        }

    }

}