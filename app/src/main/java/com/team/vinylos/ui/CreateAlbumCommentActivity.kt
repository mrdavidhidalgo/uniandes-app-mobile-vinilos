package com.team.vinylos.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.team.vinylos.R
import com.team.vinylos.databinding.ActivityCreateAlbumBinding
import com.team.vinylos.databinding.ActivityCreateAlbumCommentBinding
import com.team.vinylos.models.AlbumCommentRequest
import com.team.vinylos.models.AlbumRequest
import com.team.vinylos.models.Collector
import com.team.vinylos.ui.adapters.AlbumAdapter
import com.team.vinylos.viewmodels.AlbumViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateAlbumCommentActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumViewModel
    private var albumAdapter: AlbumAdapter? = null
    private lateinit var albumReleaseDateField: EditText
    private lateinit var albumId : Integer

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityCreateAlbumCommentBinding.inflate(layoutInflater)

        setContentView(binding.root)

        albumAdapter = AlbumAdapter()
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)


        var createAlbumCommentButton: Button = binding.createAlbumCommentButton

        var cancelCreateAlbumButton: Button = binding.canceCreateAlbumCommentButton

        val bundle: Bundle? = intent.extras
        val album: String? = bundle!!.get("id").toString()
        albumId = Integer(album)

        createAlbumCommentButton.setOnClickListener {

            // Do click handling here
            var albumCommentDescriptionEditText: EditText = findViewById(R.id.commentDescription)
            var albumCommentDescription = albumCommentDescriptionEditText.text.toString()

            var albumCommentRatingEditText: EditText = findViewById(R.id.commentRating)
            var albumCommentRating = albumCommentRatingEditText.text.toString()

            if(albumCommentDescription.isEmpty()){
                albumCommentDescriptionEditText.error = "Debes diligenciar el comentario del álbum"
                return@setOnClickListener
            }
            if(albumCommentRating.isEmpty()){
                albumCommentRatingEditText.error = "Debes diligenciar la calificación a realizar"
                return@setOnClickListener
            }

            if(Integer.valueOf(albumCommentRating) > 5 ||  Integer.valueOf(albumCommentRating)< 1){
                albumCommentRatingEditText.error = "La calificación debe estar entre 1 y 5"
                return@setOnClickListener
            }

            var collector = Collector(100, "Testing", "64652154", "test@gmail.com")

            var commentRequest = AlbumCommentRequest(
                albumCommentDescription, Integer(albumCommentRating), collector
            )

            createAlbumComment(albumId, commentRequest)

            val intent = Intent(this, AlbumActivity::class.java)
            startActivity(intent)


        }

        cancelCreateAlbumButton.setOnClickListener {
            openCancelDialog(Intent(this, AlbumActivity::class.java))
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.artists-> {
                    false
                }
                R.id.collectors-> {
                    false
                }
                R.id.albums->{
                    false
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

    private fun createAlbumComment(albumId : Integer, commentRequest: AlbumCommentRequest) {
        lifecycleScope.launch {

            viewModel.createAlbumComment(albumId, commentRequest)
            val toast =
                Toast.makeText(applicationContext, "Se ha registrado el comentario del album exitosamente", Toast.LENGTH_LONG)
            toast.show()

        }

    }

    private fun openCancelDialog(intent: Intent){
        val builder = AlertDialog.Builder(this@CreateAlbumCommentActivity)
        builder.setMessage("Quieres cancelar la creación del comentario del albúm?")
        builder.setTitle("Alerta!")
        builder.setCancelable(false)
        builder.setPositiveButton("Si",
            DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                //val intent = Intent(this, AlbumActivity::class.java)
                startActivity(intent)
            })

        builder.setNegativeButton("No",
            DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                dialog.cancel()
            } as DialogInterface.OnClickListener)

        val alertDialog = builder.create()
        alertDialog.show()
    }

}