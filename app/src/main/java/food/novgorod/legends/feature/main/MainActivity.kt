package food.novgorod.legends.feature.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import food.novgorod.legends.R
import food.novgorod.legends.data.User
import food.novgorod.legends.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportFragmentManager.beginTransaction()
//            .add(MapFragment.newInstance(), "MapFragment")
//            .commit()
//        startActivity(Intent(this, MapsActivity::class.java))
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.setMinZoomPreference(10.0f);
        googleMap.setMaxZoomPreference(20.0f);
        // Add a marker in Sydney and move the camera
        val novgorodCoordinate = LatLng(55.002021, 82.956043)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(novgorodCoordinate))
    }
    companion object{
        const val KEY_USER = "KEY_USER==KEY_USER"
        fun intentMainActivity(context: Context, user: User) = Intent(context , MainActivity::class.java).apply{
            putExtra(KEY_USER , user)
        }
    }
}