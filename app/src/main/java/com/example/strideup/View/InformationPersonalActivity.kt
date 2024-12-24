package com.example.strideup.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.strideup.databinding.ActivityInformationpersonalBinding

class InformationPersonalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationpersonalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializar View Binding
        binding = ActivityInformationpersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar clic en navigate_before para volver a ActivityInicio
        binding.navigateBefore.setOnClickListener {
            navigateToInicio()
        }
    }

    private fun navigateToInicio() {
        val intent = Intent(this, InicioActivity::class.java)
        startActivity(intent)
        finish() // Finalizar ActivityInformationPersonal para evitar que quede en el stack
    }
}

