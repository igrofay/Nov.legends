package food.novgorod.legends.domain.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import food.novgorod.legends.feature.app.toMD5

object FirebaseRouteProvider {
    private const val USERS = "users"
    private const val FEEDBACKS = "feedbacks"
    private const val PLACES = "places"

    fun getReference(firebaseRoute: FirebaseRoute): DatabaseReference {
        val firebaseInstance = FirebaseDatabase
            .getInstance("https://novgorod-legends-default-rtdb.europe-west1.firebasedatabase.app")

        return when(firebaseRoute) {
            is FirebaseRoute.UsersRoute -> firebaseInstance.getReference(USERS)
            is FirebaseRoute.UserRoute -> firebaseInstance.getReference(USERS).child(firebaseRoute.nickname.toMD5())
            is FirebaseRoute.CreateFeedback -> firebaseInstance.getReference(FEEDBACKS).child(firebaseRoute.feedback.feedbackId.toMD5())
            is FirebaseRoute.FeedbacksOfPlace -> firebaseInstance.getReference(PLACES).child(firebaseRoute.placeId.toMD5()).child(FEEDBACKS)
            is FirebaseRoute.Place -> firebaseInstance.getReference(PLACES).child(firebaseRoute.placeId.toMD5())
            is FirebaseRoute.Places -> firebaseInstance.getReference(PLACES)
            else -> firebaseInstance.reference
        }
    }
}