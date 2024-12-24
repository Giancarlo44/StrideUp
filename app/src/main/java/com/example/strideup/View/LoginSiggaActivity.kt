package com.example.strideup.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.strideup.databinding.ActivityLoginsiigaBinding

class LoginSiggaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginsiigaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginsiigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigateBefore.setOnClickListener {
            navigateToLoginPrincipal()
        }

        binding.btnContinuar.setOnClickListener {
            navigateToInicio()
        }
    }

    private fun navigateToLoginPrincipal() {
        val intent = Intent(this, LoginPrincipalActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun navigateToInicio() {
        val intent = Intent(this, InicioActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}




