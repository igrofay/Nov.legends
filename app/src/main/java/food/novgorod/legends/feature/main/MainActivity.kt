package food.novgorod.legends.feature.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.places.Places
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.*

import food.novgorod.legends.R
import food.novgorod.legends.data.LoadState
import food.novgorod.legends.data.MapPropertiesProvider
import food.novgorod.legends.feature.profile.ProfileFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import android.graphics.Bitmap

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable

import android.graphics.Canvas
import android.graphics.Color
import android.widget.Toast

import androidx.annotation.DrawableRes

import com.google.android.gms.maps.model.BitmapDescriptor
import food.novgorod.legends.data.PlaceObject
import food.novgorod.legends.databinding.ActivityMainBinding
import food.novgorod.legends.domain.place.PlaceRepository
import food.novgorod.legends.feature.descriptionplace.DescriptionPlaceBottomSheetFragment
import food.novgorod.legends.feature.services.GPSTracker
import com.google.android.gms.maps.model.JointType

import com.google.android.gms.maps.model.RoundCap

import com.google.android.gms.maps.model.PolylineOptions





class MainActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var googleMap: GoogleMap
    private val markers: MutableList<Marker> = mutableListOf()
    lateinit var gpsTracker: GPSTracker
    lateinit var route: MutableList<PlaceObject>

    var canGetLocation = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)

        getLocationPermission()

        binding.myLocation.setOnClickListener {
            if (canGetLocation) {
                moveCameraOnLocation()
            }
        }

        binding.bildRoute.setOnClickListener {
            addPolyLine(PlaceRepository.listPlace)
        }

        route = mutableListOf()


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.nextToProfile.setOnClickListener {
            supportFragmentManager.commit {
                replace<ProfileFragment>(R.id.content, "tag")
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.setMinZoomPreference(5.0f);
        googleMap.setMaxZoomPreference(30.0f);
        try {

            val success: Boolean = googleMap.setMapStyle(
                MapStyleOptions(MapPropertiesProvider.provideLightThemeStylesheet())
            )
            if (!success) {
                Log.e("MapsActivityRaw", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("MapsActivityRaw", "Can't find style.", e)
        }
        val novgorodCoordinate = LatLng(58.522869, 31.269793)

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(novgorodCoordinate))

        createNewMarkers(PlaceRepository.listPlace)

        googleMap.setOnMarkerClickListener(this)

        if (canGetLocation) {
            googleMap.isMyLocationEnabled = true
        }

        googleMap.uiSettings.isMyLocationButtonEnabled = false

        //collectMarkers()
    }

//    private fun collectMarkers() {
//        lifecycleScope.launch {
//            viewModel.placeLoadState.collect {
//                Log.e("TEST", "$it")
//                if (it is LoadState.Loaded) {
//                    val markers = it.result as List<Place>
//                    clearMarkers()
//                    createNewMarkers(markers)
//                }
//            }
//        }
//    }

    private fun createNewMarkers(markers: List<PlaceObject>)  {
        markers.forEach {
            if(it.coor == "") return@forEach
            val lat = it.coor.split(", ")
            val latLng = LatLng(lat[0].toDouble(), lat[1].toDouble())
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .icon(bitmapDescriptorFromVector(this, R.drawable.ic_orangemarker))
                    .title(it.type)
            ) ?: return
            marker.tag = it.id
        }
    }

    private fun clearMarkers() {
        markers.forEach {
            it.remove()
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        DescriptionPlaceBottomSheetFragment().show(supportFragmentManager , marker.tag?.toString())
        Toast.makeText(this, marker.tag?.toString(), Toast.LENGTH_SHORT).show()
        return true
    }
    private fun bitmapDescriptorFromVector(
        context: Context,
        @DrawableRes vectorDrawableResourceId: Int
    ): BitmapDescriptor? {
        val background = ContextCompat.getDrawable(context, R.drawable.marker_background)
        background!!.setBounds(0, 0, background.intrinsicWidth, background.intrinsicHeight)
        val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            background.intrinsicWidth,
            background.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        background.draw(canvas)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    fun moveCameraOnLocation() {
        val pos = LatLng(gpsTracker.latitude, gpsTracker.longitude)
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(10F));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(pos))
    }

    lateinit var tempObject: PlaceObject
    // можно ставить несколько точек(больше 2х) и будет рисовать линию поочерёдно
    fun addPolyLine(markers: List<PlaceObject>) {
        var plo = PolylineOptions()
        var count = 0
        route.clear()
        googleMap.clear()
        createNewMarkers(PlaceRepository.listPlace)

        while (count < 6) {
            tempObject = markers.random()
            if (tempObject.coor != "") {
                if (tempObject in route) {

                } else {
                    route.add(tempObject)

                    val lat = tempObject.coor.split(", ")
                    val latLng = LatLng(lat[0].toDouble(), lat[1].toDouble())
                    plo.add(latLng)

                    count ++
                }
            }
        }

        plo.color(Color.RED)
        plo.geodesic(true)
        plo.startCap(RoundCap())
        plo.width(17f)
        plo.jointType(JointType.BEVEL)
        googleMap.addPolyline(plo)
    }

    private fun getLocationPermission() {

        if (ContextCompat.checkSelfPermission(this@MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION) !==
            PackageManager.PERMISSION_GRANTED) {
                Log.e("LLLLLLL", "PINOINWRFR")
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )

            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            }
        } else {
            canGetLocation = true
            gpsTracker = GPSTracker(this@MainActivity, this@MainActivity)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) ===
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        canGetLocation = true
                        googleMap.isMyLocationEnabled = true
                        gpsTracker = GPSTracker(this@MainActivity, this@MainActivity)
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}