package food.novgorod.legends.domain.firebase

sealed class FirebaseRoute {
    object UsersRoute: FirebaseRoute()
    data class UserRoute(val nickname: String): FirebaseRoute()
}
