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
import com.team.vinylos.models.Album

class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recyclerView = binding.albumsRv
        // Sets the LinearLayoutManager of the recyclerview
        //recyclerView.layoutManager = LinearLayoutManager(this)

        val list = listOf(Album(albumId = 1,name = "Shakira",
            cover = "https://depor.com/resizer/HQgjD5K6SDwjYkurI7FLcucvR00=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/H42EXSOSQVCQ7HWEAIFVJNKH2Y.jpg",
            recordLabel = "aaa",
            releaseDate = "date",
            genre = "f",
            description = "Cantante"),

            Album(albumId = 2,name = "Franklin Candanoza",
            cover = "https://www.elpais.com.co/files/article_main/uploads/2017/01/26/588a4f542d667.jpeg",
            recordLabel = "aaa",
            releaseDate = "date",
            genre = "f",
            description = "Desarrollador de software"))
        recyclerView.adapter = AlbumAdapter(list)
        recyclerView.layoutManager= LinearLayoutManager(this);
    }






}