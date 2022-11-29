package com.team.vinylos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityArtistBinding
import com.team.vinylos.databinding.ActivityPrizeBinding
import com.team.vinylos.ui.adapters.ArtistAdapter
import com.team.vinylos.models.Artist
import com.team.vinylos.models.Prize
import com.team.vinylos.ui.adapters.PrizeAdapter
import com.team.vinylos.viewmodels.ArtistViewModel
import com.team.vinylos.viewmodels.PrizeViewModel

class PrizeActivity : AppCompatActivity() {

    private lateinit var viewModel: PrizeViewModel
    private var prizeAdapter: PrizeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPrizeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.prizesRv


        prizeAdapter= PrizeAdapter()
        recyclerView.adapter = prizeAdapter
        recyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProvider(this).get(PrizeViewModel::class.java)
        viewModel.prizes.observe(this, Observer<List<Prize>> {
            it.apply {
                prizeAdapter!!.prizes = this
            }
        })

        viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        binding.createPrizeButton.setOnClickListener {
            val intent = Intent(this, CreatePrizeActivity::class.java)
            startActivity(intent)
        }

        binding.bottomNavigation.selectedItemId = R.id.prizes

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
                    val intent = Intent(this, ArtistActivity::class.java)
                    startActivity(intent)

                    true
                }
                R.id.prizes-> {

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