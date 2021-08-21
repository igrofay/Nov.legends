package food.novgorod.legends.feature.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.database.FirebaseDatabase
import food.novgorod.legends.R
import food.novgorod.legends.data.LoadState
import food.novgorod.legends.data.models.Place
import food.novgorod.legends.data.models.Tag
import food.novgorod.legends.databinding.ActivityWelcomeBinding
import food.novgorod.legends.domain.firebase.FirebaseRoute
import food.novgorod.legends.domain.firebase.FirebaseRouteProvider
import food.novgorod.legends.feature.main.MainActivity
import kotlinx.coroutines.flow.collect

class WelcomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityWelcomeBinding
    lateinit var viewModel: WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
    }

}
