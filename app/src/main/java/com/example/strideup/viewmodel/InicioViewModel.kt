package com.example.strideup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class InicioViewModel : ViewModel() {
    // LiveData para la ubicación inicial del mapa
    private val _initialLocation = MutableLiveData<LatLng>()
    val initialLocation: LiveData<LatLng> get() = _initialLocation

    // LiveData para manejar la navegación
    private val _navigateTo = MutableLiveData<String?>()
    val navigateTo: LiveData<String?> get() = _navigateTo

    init {
        // Configurar la ubicación inicial en la Universidad Nacional del Santa
        _initialLocation.value = LatLng(-9.120868355359484, -78.51331015517353)
    }

    fun onNavigateTo(destination: String) {
        _navigateTo.value = destination
    }

    fun onNavigationCompleted() {
        _navigateTo.value = null
    }
}



