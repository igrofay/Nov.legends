package food.novgorod.legends.data

import android.os.Parcelable
import android.os.Parcel

class User() : Parcelable {
    var firstName: String? = null
    var lastName: String? = null
    var imagePath: String? = null

    constructor(parcel: Parcel) : this() {
        firstName = parcel.readString()
        lastName = parcel.readString()
        imagePath = parcel.readString()
    }

    public constructor(firstName: String?, lastName: String?, imagePath: String?) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.imagePath = imagePath
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(imagePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }


}