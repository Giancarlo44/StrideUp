package com.example.strideup.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.strideup.R
import com.example.strideup.databinding.ActivityInicioBinding
import com.example.strideup.viewmodel.InicioViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class InicioActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityInicioBinding
    private lateinit var mMap: GoogleMap
    private val viewModel: InicioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar View Binding
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el fragmento del mapa
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Configurar clics de botones con ViewModel
        binding.profileImage.setOnClickListener { viewModel.onNavigateTo("InformationPersonal") }
        binding.Estrella.setOnClickListener { viewModel.onNavigateTo("Tienda") }
        binding.Trofeo.setOnClickListener { viewModel.onNavigateTo("Ranking") }
        binding.buttonChevron.setOnClickListener { viewModel.onNavigateTo("Histogeneral") }
        binding.lupa.setOnClickListener { viewModel.onNavigateTo("Busqueda") }
        binding.buscamapa.setOnClickListener { viewModel.onNavigateTo("Busqueda") }
        binding.expand.setOnClickListener { viewModel.onNavigateTo("Mapa") }

        // Observar cambios en la navegación
        viewModel.navigateTo.observe(this, Observer { destination ->
            destination?.let {
                navigateToDestination(it)
                viewModel.onNavigationCompleted()
            }
        })
    }

    private fun navigateToDestination(destination: String) {
        val intent = when (destination) {
            "InformationPersonal" -> Intent(this, InformationPersonalActivity::class.java)
            "Tienda" -> Intent(this, TiendaActivity::class.java)
            "Ranking" -> Intent(this, RankingActivity::class.java)
            "Histogeneral" -> Intent(this, HistogeneralActivity::class.java)
            "Busqueda" -> Intent(this, BusquedaActivity::class.java)
            "Mapa" -> Intent(this, MapaActivity::class.java)
            else -> null
        }
        intent?.let { startActivity(it) }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Observar la ubicación inicial desde el ViewModel
        viewModel.initialLocation.observe(this) { unsLocation ->
            // Agregar marcador y mover la cámara a la ubicación inicial
            mMap.addMarker(
                MarkerOptions().position(unsLocation).title("Universidad Nacional del Santa")
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unsLocation, 16f)) // Nivel de zoom

            // Habilitar controles del mapa
            mMap.uiSettings.isZoomControlsEnabled = true
            mMap.uiSettings.isCompassEnabled = true
        }

        enableMyLocation()
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation()
        } else {
            Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }
}
