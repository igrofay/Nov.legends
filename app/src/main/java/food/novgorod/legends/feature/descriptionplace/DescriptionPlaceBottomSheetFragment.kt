package food.novgorod.legends.feature.descriptionplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import food.novgorod.legends.R
import food.novgorod.legends.databinding.FragmentDescriptionPlaceBinding





class DescriptionPlaceBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDescriptionPlaceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionPlaceBinding.inflate(inflater,container,false)
        return binding.root
    }
}