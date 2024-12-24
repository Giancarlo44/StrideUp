package com.example.strideup.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.strideup.databinding.ActivityLoginprincipalBinding
import com.example.strideup.viewmodel.LoginPrincipalViewModel

class LoginPrincipalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginprincipalBinding
    private val viewModel: LoginPrincipalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginprincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSiiga.setOnClickListener {
            viewModel.onSiggaLoginClicked()
        }

        binding.buttonMicrosoft.setOnClickListener {
            viewModel.onMicrosoftLoginClicked()
        }

        viewModel.navigateTo.observe(this, Observer { destination ->
            destination?.let {
                when (it) {
                    "LoginSigga" -> navigateToLoginSigga()
                    "LoginMicrosoft" -> navigateToLoginMicrosoft()
                }
                viewModel.onNavigationCompleted()
            }
        })
    }

    private fun navigateToLoginSigga() {
        val intent = Intent(this, LoginSiggaActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun navigateToLoginMicrosoft() {
        val intent = Intent(this, LoginMicrosoftActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}



