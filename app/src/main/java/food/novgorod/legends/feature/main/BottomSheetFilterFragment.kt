package food.novgorod.legends.feature.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import food.novgorod.legends.R
import food.novgorod.legends.databinding.FragmentFilterBinding

class BottomSheetFilterFragment : BottomSheetDialogFragment(){
    lateinit var binding : FragmentFilterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val  viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        binding.checkBox1.isChecked = viewModel.hashMap[R.string.legends] == true
        binding.checkBox2.isChecked = viewModel.hashMap[R.string.tradition] == true
        binding.checkBox3.isChecked = viewModel.hashMap[R.string.fairy_tales] == true
        binding.checkBox4.isChecked = viewModel.hashMap[R.string.lake] == true
        binding.checkBox5.isChecked = viewModel.hashMap[R.string.source] == true
        binding.checkBox6.isChecked = viewModel.hashMap[R.string.stone] == true
        binding.checkBox7.isChecked = viewModel.hashMap[R.string.other] == true
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val  viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.hashMap[R.string.legends] = binding.checkBox1.isChecked
        viewModel.hashMap[R.string.tradition] =binding.checkBox2.isChecked
        viewModel.hashMap[R.string.fairy_tales] =binding.checkBox3.isChecked
        viewModel.hashMap[R.string.lake] = binding.checkBox4.isChecked
        viewModel.hashMap[R.string.source] = binding.checkBox5.isChecked
        viewModel.hashMap[R.string.stone] = binding.checkBox6.isChecked
        viewModel.hashMap[R.string.other] = binding.checkBox7.isChecked
    }
}