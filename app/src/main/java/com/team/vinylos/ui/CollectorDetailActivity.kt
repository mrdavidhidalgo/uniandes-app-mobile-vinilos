package com.team.vinylos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityCollectorBinding
import com.team.vinylos.databinding.CollectorDetailBinding
import com.team.vinylos.models.Album
import com.team.vinylos.models.Collector
import com.team.vinylos.ui.adapters.CollectorAdapter
import com.team.vinylos.ui.adapters.CollectorDetailAdapter
import com.team.vinylos.viewmodels.CollectorDetailViewModel
import com.team.vinylos.viewmodels.CollectorViewModel

class CollectorDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CollectorDetailViewModel
    private var collectorAdapter: CollectorDetailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = CollectorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.albumsRv;


        val bundle: Bundle? = intent.extras
        val collectorId: String? = bundle!!.get("id").toString()

        collectorAdapter= CollectorDetailAdapter()
        recyclerView.adapter = collectorAdapter
        recyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProvider(this).get(CollectorDetailViewModel::class.java)
        viewModel.refreshCollector(Integer(collectorId))
        viewModel.collector.observe(this, Observer<Collector>{
            it.apply {
                binding.collector=this
                binding.title1.text="  "+this.name.uppercase().get(0)
            }
        })
        viewModel.albums.observe(this, Observer<List<Album>>{
            it.apply {
                collectorAdapter!!.albums = this
            }
        })
        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
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
                    val intent = Intent(this, CollectorActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

}