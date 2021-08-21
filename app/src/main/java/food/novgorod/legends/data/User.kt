package food.novgorod.legends.data

import android.os.Parcelable
import android.os.Parcel

data class User(
    var firstName: String = "",
    var phone: String = "",
    var imagePath: String = ""
)