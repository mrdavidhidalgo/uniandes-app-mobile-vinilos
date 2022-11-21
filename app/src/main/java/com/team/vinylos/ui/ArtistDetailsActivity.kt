package com.team.vinylos.ui

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityArtistdetailBinding
import com.team.vinylos.models.Artist
import com.team.vinylos.ui.adapters.ArtistDetailAdapter
import com.team.vinylos.viewmodels.ArtistDetailsViewModel


class ArtistDetailsActivity : AppCompatActivity() {

    //val idArtist: Int =1

    private lateinit var viewModel: ArtistDetailsViewModel
    private var artistAdapter: ArtistDetailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle? = intent.extras
        bundle?.let {
            bundle.apply {
                val artistName: String? = getString("name")
                Log.d("variable", artistName.toString())
            }
        }
        val artistName2: String? = bundle!!.get("name").toString()
        Log.d("NombreArtista", artistName2.toString())
        val idArtistS: String? = bundle!!.get("id").toString()
        val artistId: Int = idArtistS!!.toInt()
        Log.d("Id Artista", artistId.toString())
        val binding = ActivityArtistdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.artistDt

        //val i = Intent(this, ArtistDetailsActivity::class.java)

        //val idArtist: Int = i.getIntExtra("id", 1000)
        //Log.d("variable", idArtist.toString())


        artistAdapter= ArtistDetailAdapter()
        recyclerView.adapter = artistAdapter
        recyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProvider(this).get(ArtistDetailsViewModel::class.java)
        //viewModel = ViewModelProvider(this).get(artistId.toString(), ArtistDetailsViewModel::class.java)
        viewModel.setprueba(artistId)
        viewModel.artists.observe(this, Observer<Artist> {
            it.apply {
                artistAdapter!!.artists = this
            }
        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
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

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}