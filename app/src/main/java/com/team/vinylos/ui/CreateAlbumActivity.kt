package com.team.vinylos.ui

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityCreateAlbumBinding
import com.team.vinylos.ui.adapters.AlbumAdapter
import com.team.vinylos.viewmodels.AlbumViewModel
import java.text.SimpleDateFormat
import androidx.lifecycle.lifecycleScope
import com.team.vinylos.models.AlbumRequest
import kotlinx.coroutines.launch

class CreateAlbumActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumViewModel
    private var albumAdapter: AlbumAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityCreateAlbumBinding.inflate(layoutInflater)

        setContentView(binding.root)

        albumAdapter= AlbumAdapter()
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)



        var createAlbumButton : Button = binding.createAlbumButton

        createAlbumButton.setOnClickListener {
            print("Creando el album")

            // Do click handling here
            var albumNameEditText: EditText = findViewById(R.id.albumName)
            var albumName = albumNameEditText.text.toString()

            var albumDescriptionEditText: EditText = findViewById(R.id.albumDescription)
            var albumDescription = albumDescriptionEditText.text.toString()

            var albumGenreSpinner: Spinner = findViewById(R.id.albumGenre)
            var albumGenre = albumGenreSpinner.selectedItem.toString()

            var albumRecordLabelSpinner: Spinner = findViewById(R.id.albumRecordLabel)
            var albumRecordLabel = albumRecordLabelSpinner.selectedItem.toString()

            var albumCoverEditText: EditText = findViewById(R.id.albumCover)
            var albumCover = albumCoverEditText.text.toString()

            var albumReleaseDateEditText: EditText = findViewById(R.id.albumReleaseDate)
            val albumReleaseDateFormatted = SimpleDateFormat("yyyy-MM-dd").format(
                SimpleDateFormat("dd/MM/yyyy").parse(albumReleaseDateEditText.text.toString())
            )

            val releaseDate = albumReleaseDateFormatted.toString()

            var albumRequest = AlbumRequest(albumName, albumCover, releaseDate, albumDescription, albumGenre, albumRecordLabel)

            createAlbum(albumRequest)
            val intent = Intent(this, AlbumActivity::class.java)
            startActivity(intent)

        }

    }

    private fun createAlbum(albumRequest: AlbumRequest) {
        lifecycleScope.launch {

            viewModel.createAlbum(albumRequest)
            val toast =
                Toast.makeText(applicationContext, "Se ha creado el album exitosamente", Toast.LENGTH_LONG)
            toast.show()
        }

    }


}