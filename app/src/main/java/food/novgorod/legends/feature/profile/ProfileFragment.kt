package food.novgorod.legends.feature.profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import food.novgorod.legends.R
import food.novgorod.legends.databinding.EditProfileDialogBinding
import food.novgorod.legends.databinding.FragmentProfileBinding
import food.novgorod.legends.domain.user.UserRepository
import food.novgorod.legends.feature.app.App


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        val user = UserRepository.currentUser
        Glide.with(this).load(user?.imagePath)
            .error(R.drawable.ic_user).into(binding.iconProfile)
        binding.textView4.text = user?.name
        binding.backProfile.setOnClickListener{
            fragmentManager?.popBackStack()
        }
        binding.imageTheme.setOnClickListener {
            val shered = requireContext()
                .getSharedPreferences(App.prefTag, Context.MODE_PRIVATE)
            val isDark = ! shered.getBoolean(App.DARK, false)
            AppCompatDelegate.setDefaultNightMode(
                if(isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
            shered.edit().putBoolean(App.DARK , isDark).apply()
        }
        binding.editProfile.setOnClickListener {
            DialogEditProfile().show(childFragmentManager , "TAR")
        }
        return binding.root
    }



}