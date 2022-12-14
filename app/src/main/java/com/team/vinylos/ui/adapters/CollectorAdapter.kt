package com.team.vinylos.ui.adapters

import android.content.Intent
import android.util.Log
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
import com.team.vinylos.databinding.CollectorItemBinding
import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector
import com.team.vinylos.ui.ArtistDetailsActivity
import com.team.vinylos.ui.CollectorDetailActivity

class CollectorAdapter() : RecyclerView.Adapter<CollectorAdapter.CollectorViewHolder>() {

    var collectors: List<Collector> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false
        )
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }
        holder.bind(collectors[position])
        holder.viewDataBinding.root.setOnClickListener {

            val collector = collectors[position]
            Log.d("Enviando Id:", collector.id.toString())
            val activity = holder.itemView.context
            val i = Intent(activity, CollectorDetailActivity::class.java)
            i.putExtra("id", collector.id.toString())
            i.putExtra("name", collector.name)
            activity.startActivity(i)
        }
    }



    override fun getItemCount(): Int {
        return collectors.size
    }

    class CollectorViewHolder(val viewDataBinding: CollectorItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_item
        }

        fun bind(collector: Collector) {
            viewDataBinding.title1.text="  "+collector.name.uppercase().get(0)
        }

    }

}