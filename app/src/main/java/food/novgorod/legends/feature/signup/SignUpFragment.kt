package food.novgorod.legends.feature.signup


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import food.novgorod.legends.R
import food.novgorod.legends.databinding.FragmentSignUpBinding
import android.widget.Toast

import android.net.Uri

import androidx.activity.result.contract.ActivityResultContracts
import food.novgorod.legends.MainActivity
import food.novgorod.legends.WelcomeActivity
import food.novgorod.legends.WelcomeActivity.Companion.APP_PREFERENCES
import food.novgorod.legends.WelcomeActivity.Companion.KEY_FIRSTNAME
import food.novgorod.legends.WelcomeActivity.Companion.KEY_IMAGE
import food.novgorod.legends.WelcomeActivity.Companion.KEY_LASTNAME
import food.novgorod.legends.data.User


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding?=null
    private val binding get() = _binding!!
    private var imagePath : String? =null
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent())  { uri: Uri? ->
        if (uri != null){
            imagePath = uri.path
            binding.imageView2.setImageURI(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView3.setOnClickListener{
            if(checkText()){
                val sharedPreferencesEdit = requireContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).edit()
                sharedPreferencesEdit.putString(KEY_FIRSTNAME , binding.inputFirstname.text.toString())
                sharedPreferencesEdit.putString(KEY_LASTNAME , binding.inputLastname.text.toString())
                sharedPreferencesEdit.putString(KEY_IMAGE , imagePath ?: "" )
                sharedPreferencesEdit.apply()
                val user = User(binding.inputFirstname.text.toString() , binding.inputLastname.text.toString(), imagePath)
                requireActivity().apply {
                    startActivity(
                        MainActivity.intentMainActivity(requireContext() , user )
                    ) }.finish()
            }else{
                Toast.makeText(requireContext() , R.string.empty_text , Toast.LENGTH_SHORT).show()
            }
        }
        binding.addImage.setOnClickListener {
            getContent.launch("image/*")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkText(): Boolean{
        val textLog = binding.inputFirstname.text.toString()
        val strListLog: List<String> = textLog.split(" ")
        val textPassword = binding.inputLastname.text.toString()
        val strListPassword: List<String> = textPassword.split(" ")
        return strListLog.isNotEmpty() && textLog != ""
                && strListPassword.isNotEmpty() && textPassword != ""

    }


}