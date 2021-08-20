package food.novgorod.legends.data

import android.os.Parcelable
import android.os.Parcel

data class User(
    var firstName: String? = null,
    var lastName: String? = null,
    var imagePath: String? = null
)