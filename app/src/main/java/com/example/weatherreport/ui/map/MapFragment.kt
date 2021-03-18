package com.example.weatherreport.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.weatherreport.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import moxy.MvpAppCompatFragment

class MapFragment : MvpAppCompatFragment(R.layout.fragment_map), MapView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mapView) {
            onCreate(null)
            getMapAsync {
                MapsInitializer.initialize(activity?.applicationContext)
                setMapLocation(it)
            }
        }
    }

    private fun setMapLocation(map: GoogleMap) {
        with(map) {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            if (checkPermissions()) {
                setCurrentLocation(this)
            } else {
                requestMapPermissions()
            }

            setOnMyLocationButtonClickListener {
                setCurrentLocation(this)
                true
            }

            setOnMapClickListener {
                Toast.makeText(context, "Clicked on map", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setCurrentLocation(map: GoogleMap) {
        map.uiSettings?.isMyLocationButtonEnabled = true
        map.isMyLocationEnabled = true

        LocationServices.getFusedLocationProviderClient(requireContext()).lastLocation.addOnCompleteListener {
            val position = LatLng(it.result.latitude, it.result.longitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13f))
            map.addMarker(MarkerOptions().position(position))
        }
    }

    private fun requestMapPermissions() {
        if (!checkPermissions()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1488
            )
        }
    }

    private fun checkPermissions() = ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    override fun onResume() {
        mapView?.onResume()
        super.onResume()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1488) {
            mapView?.onResume()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}