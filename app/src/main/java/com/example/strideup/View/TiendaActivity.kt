package com.example.strideup.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.strideup.databinding.ActivityTiendaBinding

class TiendaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTiendaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar View Binding
        binding = ActivityTiendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar clic en navigate_before para volver a InicioActivity
        binding.btnBack.setOnClickListener {
            navigateToInicio()
        }
    }

    private fun navigateToInicio() {
        val intent = Intent(this, InicioActivity::class.java)
        startActivity(intent)
        finish() // Finalizar TiendaActivity para evitar que quede en el stack
    }
}
