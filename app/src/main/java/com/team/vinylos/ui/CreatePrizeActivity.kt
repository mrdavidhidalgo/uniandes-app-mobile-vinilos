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
import com.team.vinylos.databinding.ActivityCreatePrizeBinding
import com.team.vinylos.models.AlbumRequest
import com.team.vinylos.models.PrizeRequest
import com.team.vinylos.ui.adapters.AlbumAdapter
import com.team.vinylos.ui.adapters.PrizeAdapter
import com.team.vinylos.viewmodels.AlbumViewModel
import com.team.vinylos.viewmodels.PrizeViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreatePrizeActivity : AppCompatActivity(){

    private lateinit var viewModel: PrizeViewModel
    private var prizeAdapter: PrizeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityCreatePrizeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        prizeAdapter = PrizeAdapter()
        viewModel = ViewModelProvider(this).get(PrizeViewModel::class.java)


        var createPrizeButton: Button = binding.createPrizeButton
        var cancelCreatePrizeButton: Button = binding.cancelPrizeButton


        createPrizeButton.setOnClickListener {

            // Do click handling here
            var prizeNameEditText: EditText = findViewById(R.id.prizeName)
            var prizeName = prizeNameEditText.text.toString()

            var prizeOrganizationEditText: EditText = findViewById(R.id.prizeOrganization)
            var prizeOrganization = prizeOrganizationEditText.text.toString()

            var prizeDescriptionEditText: EditText = findViewById(R.id.prizeDescription)
            var prizeDescription = prizeDescriptionEditText.text.toString()


            if(prizeName.isEmpty()){
                prizeNameEditText.error = "Debes ingresar el nombre del premio"
                return@setOnClickListener
            }
            if(prizeName.length > 100) {
                prizeNameEditText.error = "El nombre del premio debe ser de máximo 100 carácteres"
                return@setOnClickListener
            }

            if(prizeDescription.isEmpty()){
                prizeDescriptionEditText.error = "Debes ingresar una descripcion del premio"
                return@setOnClickListener
            }
            if(prizeDescription.length > 150){
                prizeDescriptionEditText.error = "La descripción debe ser de máximo 150 carácteres"
                return@setOnClickListener
            }
            if(prizeOrganization.isEmpty()){
                prizeOrganizationEditText.error = "Debes ingresar el nombre de la organizacion del premio"
                return@setOnClickListener
            }
            if(prizeOrganization.length > 100){
                prizeOrganizationEditText.error = "La organizacion del premio debe ser de máximo 100 carácteres"
                return@setOnClickListener
            }

            var prizeRequest = PrizeRequest(
                prizeName,
                prizeOrganization,
                prizeDescription
            )

            createPrize(prizeRequest)
            val intent = Intent(this, PrizeActivity::class.java)
            startActivity(intent)


        }

        cancelCreatePrizeButton.setOnClickListener {
            openCancelDialog(Intent(this, PrizeActivity::class.java))
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

    private fun createPrize(prizeRequest: PrizeRequest) {
        lifecycleScope.launch {

            viewModel.createPrize(prizeRequest)
            val toast =
                Toast.makeText(applicationContext, "Se ha creado el premio exitosamente", Toast.LENGTH_LONG)
            toast.show()

        }

    }

    private fun openCancelDialog(intent: Intent){
        val builder = AlertDialog.Builder(this@CreatePrizeActivity)
        builder.setMessage("Deseas cancelar la creación del albúm?")
        builder.setTitle("Alerta!")
        builder.setCancelable(false)
        builder.setPositiveButton("Si", DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
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