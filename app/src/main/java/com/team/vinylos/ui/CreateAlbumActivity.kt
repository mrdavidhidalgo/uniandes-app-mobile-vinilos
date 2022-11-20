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
import com.team.vinylos.models.AlbumRequest
import com.team.vinylos.ui.adapters.AlbumAdapter
import com.team.vinylos.viewmodels.AlbumViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateAlbumActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: AlbumViewModel
    private var albumAdapter: AlbumAdapter? = null
    private lateinit var albumReleaseDateField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)

        val binding = ActivityCreateAlbumBinding.inflate(layoutInflater)

        setContentView(binding.root)

        albumAdapter = AlbumAdapter()
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)


        var createAlbumButton: Button = binding.createAlbumButton
        albumReleaseDateField = binding.albumReleaseDate
        var cancelCreateAlbumButton: Button = binding.canceCreateAlbumButton

        albumReleaseDateField.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Please note that use your package name here
                val mDatePickerDialogFragment: com.team.vinylos.ui.DatePicker = DatePicker()
                mDatePickerDialogFragment.show(supportFragmentManager, "DATE PICK")
            }
        })


        createAlbumButton.setOnClickListener {

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


            if(albumName.isEmpty()){
                albumNameEditText.error = "Campo obligatorio"
                return@setOnClickListener
            }
            if(albumDescription.isEmpty()){
                albumDescriptionEditText.error = "Campo obligatorio"
                return@setOnClickListener
            }
            if(albumDescription.length > 150){
                albumDescriptionEditText.error = "La descripción debe ser de máximo 150 carácteres"
                return@setOnClickListener
            }



            var albumReleaseDateEditText: EditText = findViewById(R.id.albumReleaseDate)
            val albumReleaseDateFormatted = SimpleDateFormat("yyyy-MM-dd").format(
                SimpleDateFormat("dd/MM/yyyy").parse(albumReleaseDateEditText.text.toString())
            )
            val releaseDate = albumReleaseDateFormatted.toString()

            var albumRequest = AlbumRequest(
                albumName,
                albumCover,
                releaseDate,
                albumDescription,
                albumGenre,
                albumRecordLabel
            )

            createAlbum(albumRequest)
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
                else -> false
            }
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

    private fun openCancelDialog(intent: Intent){
        val builder = AlertDialog.Builder(this@CreateAlbumActivity)
        builder.setMessage("Quieres cancelar la creación del albúm?")
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

    override fun onDateSet(p0: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val mCalendar = Calendar.getInstance()
        mCalendar[Calendar.YEAR] = year
        mCalendar[Calendar.MONTH] = month
        mCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth

        val format1 = SimpleDateFormat("dd/MM/yyyy")
        val date1 = format1.format(mCalendar.time)

        albumReleaseDateField.setText(date1)
    }


}