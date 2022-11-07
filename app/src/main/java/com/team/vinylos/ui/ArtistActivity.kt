package com.team.vinylos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityArtistBinding
import com.team.vinylos.ui.adapters.ArtistAdapter
import com.team.vinylos.models.Artist
import com.team.vinylos.viewmodels.ArtistViewModel

class ArtistActivity : AppCompatActivity() {

    private lateinit var viewModel: ArtistViewModel
    private var artistAdapter: ArtistAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.artistsRv


        artistAdapter= ArtistAdapter()
        recyclerView.adapter = artistAdapter
        recyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProvider(this).get(ArtistViewModel::class.java)
        viewModel.artists.observe(this, Observer<List<Artist>> {
            it.apply {
                artistAdapter!!.artists = this
            }
        })

        binding.bottomNavigation.selectedItemId = R.id.artists

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.albums-> {
                    val intent = Intent(this, AlbumActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.collectors-> {
                    val intent = Intent(this, CollectorActivity::class.java)
                    startActivity(intent)

                    true
                }
                R.id.artists->{
                    true
                }
                else -> false
            }
        }
    }



}