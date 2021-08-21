package food.novgorod.legends.feature.profile

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import food.novgorod.legends.R
import food.novgorod.legends.databinding.FragmentProfileBinding
import food.novgorod.legends.domain.user.UserRepository


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
        return binding.root
    }

}