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
import com.team.vinylos.databinding.PrizeItemBinding
import com.team.vinylos.models.Album
import com.team.vinylos.models.Prize

class PrizeAdapter() : RecyclerView.Adapter<PrizeAdapter.PrizeViewHolder>() {

    var prizes: List<Prize> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrizeViewHolder {
        val withDataBinding: PrizeItemBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PrizeViewHolder.LAYOUT,
            parent,
            false
        )
        return PrizeViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: PrizeViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.prize = prizes[position]
        }
        holder.bind(prizes[position])
        holder.viewDataBinding.root.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return prizes.size
    }


    class PrizeViewHolder(val viewDataBinding: PrizeItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.prize_item
        }

        fun bind(prize: Prize) {
            /*Glide.with(itemView)
                .load(prize.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image)
                )
                .into(viewDataBinding.cover)*/
        }

    }

}