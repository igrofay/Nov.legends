package food.novgorod.legends.feature.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import food.novgorod.legends.data.User
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
    companion object{
        const val KEY_USER = "KEY_USER==KEY_USER"
        fun intentMainActivity(context: Context, user: User) = Intent(context , MainActivity::class.java).apply{
            putExtra(KEY_USER , user)
        }
    }
}