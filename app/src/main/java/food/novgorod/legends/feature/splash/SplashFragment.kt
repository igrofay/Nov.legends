package food.novgorod.legends.feature.splash

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import food.novgorod.legends.R
import food.novgorod.legends.data.LoadState
import food.novgorod.legends.databinding.FragmentSplashBinding
import food.novgorod.legends.feature.main.MainActivity
import kotlinx.coroutines.flow.collect
import food.novgorod.legends.feature.descriptionplace.DescriptionPlaceBottomSheetFragment


class SplashFragment : Fragment() {

    lateinit var binding : FragmentSplashBinding
    lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.progressBar.progress = 0
        val callback = viewModel.loadUser()
        lifecycleScope.launchWhenResumed {
            callback.collect {
                binding.progressBar.progress = 100
                when(it) {
                    is LoadState.InProgress -> {}
                    is LoadState.Loaded -> {
                        goToMainActivity()
                    }
                    else -> {
                        goToAnnotation()
                    }
                }
            }
        }
        return binding.root
    }


    private fun goToAnnotation() {
        NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_annotationFragment)
    }

    private fun goToMainActivity() {
        val intent = MainActivity.getIntent(requireContext())
        startActivity(intent)
        requireActivity().finish()

    }


}