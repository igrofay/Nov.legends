package food.novgorod.legends.feature.signup


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import food.novgorod.legends.R
import food.novgorod.legends.databinding.FragmentSignUpBinding
import android.widget.Toast

import android.net.Uri

import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import food.novgorod.legends.data.LoadState

import food.novgorod.legends.data.User
import food.novgorod.legends.feature.main.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel
    private var imagePath : String? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.imageView3.setOnClickListener{
            if(checkText()){
                val user = User(binding.inputName.text.toString() , binding.inputPhone.text.toString(), imagePath ?: "")
                viewModel.saveUserData(user)

            }else{
                Toast.makeText(requireContext() , R.string.empty_text , Toast.LENGTH_SHORT).show()
            }
        }
        binding.addImage.setOnClickListener {
            getContent.launch("image/*")
        }

        return binding.root
    }

    private fun startNextActivity() {
        val intent = MainActivity.getIntent(requireContext())
        startActivity(intent)
        requireActivity().finish()
    }


    private fun checkText(): Boolean{
        val textLog = binding.inputName.text.toString()
        val strListLog: List<String> = textLog.split(" ")
        val textPassword = binding.inputPhone.text.toString()
        val strListPassword: List<String> = textPassword.split(" ")
        return strListLog.isNotEmpty() && textLog != ""
                && strListPassword.isNotEmpty() && textPassword != ""

    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? ->
        if (uri != null){
            imagePath = uri.toString()
            Glide.with(this).load(uri).into(binding.imageView2)
        }
    }


}