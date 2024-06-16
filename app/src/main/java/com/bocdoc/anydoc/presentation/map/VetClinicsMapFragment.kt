package com.bocdoc.anydoc.presentation.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.coreui.fragment.toast
import com.bocdoc.anydoc.databinding.FragmentVetClinicsMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class VetClinicsMapFragment : BindingFragment<FragmentVetClinicsMapBinding>(R.layout.fragment_vet_clinics_map), OnMapReadyCallback {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private lateinit var mMap: GoogleMap
    private var locationCallback: LocationCallback? = null

    private companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

    override fun initView() {

        with(binding.rvVetClinics) {
            adapter = VetClinicsAdapter(vetClinicsList)
        }

        // Initialize fusedLocationClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize map
        val mapFragment = childFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        requestLocationUpdates()
    }

    override fun onStop() {
        super.onStop()
        // Stop location updates if needed
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        locationCallback?.let {
            fusedLocationClient?.removeLocationUpdates(it)
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000 // Location update interval (milliseconds)
            fastestInterval = 5000 // Fastest location update interval (milliseconds)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.lastLocation?.let { location ->
                    // Add marker to map
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    mMap.addMarker(MarkerOptions().position(currentLatLng).title("Current Location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))

                    // Log the current location
                    Log.d("VetClinicsMapFragment", "Current location: $currentLatLng")
                }
            }
        }

        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, null)
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Provide explanation for location permission
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates()
            } else {
                toast("Location permission is required.")
                // Request location permission only if it's denied
                requestLocationPermission()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Enable location layer on map
            mMap.isMyLocationEnabled = true
            // Request last known location
            fusedLocationClient?.lastLocation?.addOnSuccessListener { location ->
                location?.let {
                    val currentLatLng = LatLng(it.latitude, it.longitude)
                    mMap.addMarker(MarkerOptions().position(currentLatLng).title("Current Location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                    // Log the current location
                    Log.d("VetClinicsMapFragment", "Current location: $currentLatLng")
                }
            }
        } else {
            // Show toast message if location permission is not granted
            toast("Location permission is required.")
        }
    }

    // Dummy data for route adapter
    private val vetClinicsList = listOf(
        VetClinics(image = R.drawable.img_vet_clinics_01, name = "산들동물병원", date = "10시 진료 시작", phone = "02-435-7582"),
        VetClinics(image = R.drawable.img_vet_clinics_02, name = "키움동물병원", date = "매주 일요일 휴무", phone = "02-949-7582")
    )
}
