package food.novgorod.legends.feature.descriptionplace

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import food.novgorod.legends.R
import food.novgorod.legends.databinding.FragmentDescriptionPlaceBinding
import food.novgorod.legends.domain.place.PlaceRepository


class DescriptionPlaceBottomSheetFragment : BottomSheetDialogFragment() {
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

private lateinit var binding: FragmentDescriptionPlaceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionPlaceBinding.inflate(inflater,container,false)
        val place = PlaceRepository.listPlace[tag?.toInt()?.minus(1) ?: 0]
        binding.namePlace.text = place.place
        binding.descriptionPlase.text = place.characteristic
        binding.imagesRV.adapter = AdapterImage(place.images)
        binding.imagesRV.layoutManager = LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , false)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.imagesRV)

        binding.thirdPartyPlayerView.getPlayerUiController().showFullscreenButton(true)
        binding.thirdPartyPlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = "oqH-nPCmlB0"
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })

        return binding.root
    }
}