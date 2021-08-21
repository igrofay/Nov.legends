package food.novgorod.legends.feature.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import food.novgorod.legends.R
import food.novgorod.legends.feature.descriptionplace.DescriptionPlaceBottomSheetFragment


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DescriptionPlaceBottomSheetFragment().show(childFragmentManager , "TAG")
    }
}