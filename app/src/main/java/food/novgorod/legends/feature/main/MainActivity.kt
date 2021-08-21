package food.novgorod.legends.feature.main

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import food.novgorod.legends.databinding.ActivityMainBinding

import food.novgorod.legends.R
import food.novgorod.legends.data.LoadState
import food.novgorod.legends.data.MapPropertiesProvider
import food.novgorod.legends.feature.profile.ProfileFragment
import food.novgorod.legends.data.models.Place
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import android.graphics.Bitmap

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable

import android.graphics.Canvas

import androidx.annotation.DrawableRes

import com.google.android.gms.maps.model.BitmapDescriptor





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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        setContentView(binding.root)

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

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.setMinZoomPreference(10.0f);
        googleMap.setMaxZoomPreference(20.0f);
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
        val novgorodCoordinate = LatLng(55.002021, 82.956043)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(novgorodCoordinate))

        //collectMarkers()
    }

  //  private fun collectMarkers() {
  //      lifecycleScope.launch {
  //          viewModel.placeLoadState.collect {
  //              Log.e("TEST", "$it")
  //              if (it is LoadState.Loaded) {
  //                  val markers = it.result as List<Place>
  //                  clearMarkers()
  //                  createNewMarkers(markers)
  //              }
  //          }
  //      }
  //  }

    private fun createNewMarkers(markers: List<Place>) {
        markers.forEach {
            val latLng = LatLng(it.lat, it.lng)
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .icon(bitmapDescriptorFromVector(this, R.drawable.ic_orangemarker))
                    .title(it.name)
            ) ?: return
            marker.tag = it.placeId
        }
    }

    private fun clearMarkers() {
        markers.forEach {
            it.remove()
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        Log.e("KJNSOJSR", "IJWNIFUNER")
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
}