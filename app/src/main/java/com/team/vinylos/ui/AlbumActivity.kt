package com.team.vinylos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        val list = listOf(
            Album(
                albumId = 2, name = "Franklin Candanoza",
                cover = "https://www.elpais.com.co/files/article_main/uploads/2017/01/26/588a4f542d667.jpeg",
                recordLabel = "aaa",
                releaseDate = "date",
                genre = "f",
                description = "Desarrollador de software"
            )
        )
        albumAdapter= AlbumAdapter()
        recyclerView.adapter = albumAdapter
        recyclerView.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        viewModel.albums.observe(this, Observer<List<Album>> {
            it.apply {
                albumAdapter!!.albums = this
            }
        })
    }



}