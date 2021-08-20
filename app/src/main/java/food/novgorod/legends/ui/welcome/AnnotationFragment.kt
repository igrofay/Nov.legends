package food.novgorod.legends.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import food.novgorod.legends.R
import food.novgorod.legends.databinding.FragmentAnnotationBinding


class AnnotationFragment : Fragment() {
    private var _binding: FragmentAnnotationBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnnotationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageNext.setOnClickListener{
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_annotationFragment_to_signUpFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}