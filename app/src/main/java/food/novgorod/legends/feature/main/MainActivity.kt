package food.novgorod.legends.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import food.novgorod.legends.R
import food.novgorod.legends.databinding.ActivityMainBinding
import food.novgorod.legends.feature.map.MapFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(MapFragment.newInstance(), "MapFragment")
            .commit()
    }
}