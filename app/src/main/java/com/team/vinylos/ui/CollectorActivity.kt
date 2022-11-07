package com.team.vinylos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityAlbumBinding
import com.team.vinylos.databinding.ActivityCollectorBinding
import com.team.vinylos.ui.adapters.AlbumAdapter
import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector
import com.team.vinylos.ui.adapters.CollectorAdapter
import com.team.vinylos.viewmodels.AlbumViewModel
import com.team.vinylos.viewmodels.CollectorViewModel

class CollectorActivity : AppCompatActivity() {

    private lateinit var viewModel: CollectorViewModel
    private var collectorAdapter: CollectorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCollectorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.collectorsRv

        //binding.bottomNavigation.setitem

        collectorAdapter= CollectorAdapter()
        recyclerView.adapter = collectorAdapter
        recyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProvider(this).get(CollectorViewModel::class.java)
        viewModel.collectors.observe(this, Observer<List<Collector>> {
            it.apply {
                collectorAdapter!!.collectors = this
            }
        })

        binding.bottomNavigation.selectedItemId = R.id.collectors

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.albums -> {

                    val intent = Intent(this, AlbumActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.artists-> {
                    val intent = Intent(this, ArtistActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.collectors -> {
                    true
                }
                else -> false
            }
        }
    }



}