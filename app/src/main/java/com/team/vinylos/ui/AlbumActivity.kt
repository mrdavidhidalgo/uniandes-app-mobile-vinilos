package com.team.vinylos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityAlbumBinding
import com.team.vinylos.databinding.ActivityMainBinding
import com.team.vinylos.ui.adapters.AlbumAdapter

class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.albumsRv
        // Sets the LinearLayoutManager of the recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AlbumAdapter()
    }


}