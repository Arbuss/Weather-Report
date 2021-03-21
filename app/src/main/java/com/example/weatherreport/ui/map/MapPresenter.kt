package com.example.weatherreport.ui.map

import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MapPresenter : MvpPresenter<MapView>() {
    var map: GoogleMap? = null

    fun setMapLocation() {
        map?.let {
            it.uiSettings?.isMyLocationButtonEnabled = true
            it.mapType = GoogleMap.MAP_TYPE_NORMAL
            viewState.checkPermissions(
                {
                    viewState.setCurrentLocation()
                    it.isMyLocationEnabled = true
                },
                { viewState.requestMapPermissions() })

            it.setOnMyLocationButtonClickListener {
                viewState.setCurrentLocation()
                true
            }
        }
    }

    fun setCurrentLocation(location: Location, icon: BitmapDescriptor?) {
        map?.let {
            val position = LatLng(location.latitude, location.longitude)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13f))
            it.addMarker(
                MarkerOptions()
                    .position(position)
                    .icon(icon)
            )
        }
    }
}