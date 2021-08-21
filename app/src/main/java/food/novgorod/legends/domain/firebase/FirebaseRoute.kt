package food.novgorod.legends.domain.firebase

import food.novgorod.legends.data.models.Feedback

sealed class FirebaseRoute {
    object UsersRoute: FirebaseRoute()
    data class UserRoute(val nickname: String): FirebaseRoute()
    data class FeedbacksOfPlace(val placeId: String): FirebaseRoute()
    data class CreateFeedback(val feedback: Feedback): FirebaseRoute()
    object Places: FirebaseRoute()
    data class Place(val placeId: String): FirebaseRoute(

    )
}
