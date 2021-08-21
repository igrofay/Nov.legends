package food.novgorod.legends.domain.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import food.novgorod.legends.feature.app.toMD5

object FirebaseRouteProvider {
    const val USERS = "users"

    fun getReference(firebaseRoute: FirebaseRoute): DatabaseReference {
        val firebaseInstance = FirebaseDatabase
            .getInstance("https://novgorod-legends-default-rtdb.europe-west1.firebasedatabase.app")

        return when(firebaseRoute) {
            is FirebaseRoute.UsersRoute -> firebaseInstance.getReference(USERS)
            is FirebaseRoute.UserRoute -> firebaseInstance.getReference(USERS).child(firebaseRoute.nickname.toMD5())
            else -> firebaseInstance.reference
        }
    }
}