package com.example.strideup.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Simulación: Verificar si el usuario ya está autenticado
        val isUserLoggedIn = checkUserSession()

        // Navegar según el estado de la sesión
        if (isUserLoggedIn) {
            // Si ya está autenticado, navega a la pantalla principal de tu elección
            startActivity(Intent(this, LoginPrincipalActivity::class.java)) // Cambia esto según sea necesario
        } else {
            // Si no está autenticado, navega a la pantalla de inicio de sesión
            startActivity(Intent(this, LoginPrincipalActivity::class.java))
        }

        // Finalizar esta actividad para que no quede en el stack
        finish()
    }

    private fun checkUserSession(): Boolean {
        // Implementa tu lógica aquí (por ejemplo, comprobar SharedPreferences)
        return false // Cambiar a `true` si ya hay una sesión activa
    }
}

