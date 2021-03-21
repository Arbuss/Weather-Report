package com.example.weatherreport.ui.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weatherreport.App
import com.example.weatherreport.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import kotlinx.android.synthetic.main.fragment_map.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class MapFragment : MvpAppCompatFragment(R.layout.fragment_map), MapView {

    @InjectPresenter
    lateinit var presenter: MapPresenter

    private val requestMapPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                presenter.setMapLocation()
            }
        }

    @ProvidePresenter
    fun provide() = MapPresenter().also {
        (activity?.applicationContext as App).appComponent.inject(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mapView) {
            onCreate(null)
            getMapAsync {
                MapsInitializer.initialize(activity?.applicationContext)
                presenter.map = it
                presenter.setMapLocation()
            }
        }
    }

    override fun setCurrentLocation() {
        LocationServices.getFusedLocationProviderClient(requireContext()).lastLocation.addOnCompleteListener {
            presenter.setCurrentLocation(
                it.result,
                bitmapDescriptorFromVector(requireContext(), R.drawable.ic_coronavirus)
            )
        }
    }

    override fun requestMapPermissions() {
        checkPermissions(null,
            { requestMapPermissionsLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }
        )
    }

    override fun checkPermissions(onSuccess: (() -> Unit)?, onError: (() -> Unit)?) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            onSuccess?.invoke()
        } else {
            onError?.invoke()
        }
    }

    override fun onResume() {
        mapView?.onResume()
        super.onResume()
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}