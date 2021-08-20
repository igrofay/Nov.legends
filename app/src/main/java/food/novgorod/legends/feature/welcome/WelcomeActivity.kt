package food.novgorod.legends.feature.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import food.novgorod.legends.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    companion object{
        const val KEY_FIRSTNAME = "KEY_FIRSTNAME==KEY_FIRSTNAME"
        const val KEY_LASTNAME = "KEY_LASTNAME==KEY_LASTNAME"
        const val KEY_IMAGE = "KEY_IMAGE==KEY_IMAGE"
        const val APP_PREFERENCES = "APP_PREFERENCES==APP_PREFERENCES"
    }
}
