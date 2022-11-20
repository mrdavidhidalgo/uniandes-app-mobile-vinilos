package com.team.vinylos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityAlbumBinding
import com.team.vinylos.ui.adapters.AlbumAdapter
import com.team.vinylos.models.Album
import com.team.vinylos.viewmodels.AlbumViewModel


class AlbumActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumViewModel
    private var albumAdapter: AlbumAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.albumsRv


        albumAdapter= AlbumAdapter()
        recyclerView.adapter = albumAdapter
        recyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        viewModel.albums.observe(this, Observer<List<Album>> {
            it.apply {
                albumAdapter!!.albums = this
            }
        })

        binding.bottomNavigation.selectedItemId = R.id.albums

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.artists-> {
                    val intent = Intent(this, ArtistActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.collectors-> {
                    val intent = Intent(this, CollectorActivity::class.java)
                    startActivity(intent)

                    true
                }
                R.id.albums->{
                    true
                }
                else -> false
            }
        }

        binding.createAlbumButton.setOnClickListener {
            val intent = Intent(this, CreateAlbumActivity::class.java)
            startActivity(intent)
        }
    }



}