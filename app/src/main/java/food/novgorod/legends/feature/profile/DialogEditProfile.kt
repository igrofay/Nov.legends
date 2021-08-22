package food.novgorod.legends.feature.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import food.novgorod.legends.R
import food.novgorod.legends.data.User
import food.novgorod.legends.databinding.EditProfileDialogBinding
import food.novgorod.legends.domain.user.UserRepository

class DialogEditProfile : DialogFragment() {
    lateinit var bindingDialog : EditProfileDialogBinding
    var imagePath: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDialog = EditProfileDialogBinding.inflate(inflater,container,false)
        bindingDialog.addNewImage.setOnClickListener { getContent.launch("image/*") }
        bindingDialog.saveEdit.setOnClickListener {
            if(checkText()){
                val oldUser = UserRepository.currentUser!!
                val newUser = User(bindingDialog.editName.text.toString() , oldUser.phone , imagePath ?: oldUser.imagePath)
                UserRepository.currentUser = newUser
                UserRepository.saveUserData(newUser)
                dismiss()
            }else{
                Toast.makeText(requireContext() , R.string.empty_text , Toast.LENGTH_SHORT).show()
            }
        }
        return bindingDialog.root
    }
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri? ->
        if (uri != null){
             imagePath = uri.toString()
            Glide.with(this).load(uri).into(bindingDialog.newImage)
        }
    }
    fun checkText(): Boolean{
        val textLog = bindingDialog.editName.text.toString()
        val strListLog: List<String> = textLog.split(" ")
        return strListLog.isNotEmpty() && textLog != ""
    }
}