package com.team.vinylos.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.team.vinylos.databinding.AlbumDetailBinding
import com.team.vinylos.ui.adapters.AlbumDetailAdapter
import com.team.vinylos.viewmodels.AlbumDetailViewModel
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.team.vinylos.R
import com.team.vinylos.models.Album

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumDetailViewModel
    private var albumAdapter: AlbumDetailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = AlbumDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val albumId: String? = bundle!!.get("id").toString()

        albumAdapter= AlbumDetailAdapter()


        viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        viewModel.refreshAlbum(Integer(albumId))
        viewModel.album.observe(this, Observer<Album>{
            it.apply {
                binding.album=this
                //binding.title1.text="  "+this.name.uppercase().get(0)
                Glide.with(applicationContext).load(this.cover).into(binding.imageAlbum)

            }
        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
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
                    val intent = Intent(this, AlbumActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.prizes-> {
                    val intent = Intent(this, PrizeActivity::class.java)
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