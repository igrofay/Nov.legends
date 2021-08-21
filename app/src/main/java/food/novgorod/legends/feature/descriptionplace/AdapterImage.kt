package food.novgorod.legends.feature.descriptionplace

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import food.novgorod.legends.R
import com.bumptech.glide.Glide
import food.novgorod.legends.databinding.ImageCarViewHolderBinding

class AdapterImage(var listImage: List<String>) : RecyclerView.Adapter<AdapterImage.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_car_view_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setImage(listImage[position])
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ImageCarViewHolderBinding
        fun setImage(path: String?) {
            Glide.with(itemView).load(path).into(binding.imageView4)
        }

        init {
            binding = ImageCarViewHolderBinding.bind(itemView)
        }
    }
}