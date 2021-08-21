package food.novgorod.legends.feature.main

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import food.novgorod.legends.databinding.ActivityMainBinding

import com.google.android.gms.maps.model.MapStyleOptions
import food.novgorod.legends.R
import food.novgorod.legends.data.MapPropertiesProvider
import food.novgorod.legends.feature.profile.ProfileFragment


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
    }
}