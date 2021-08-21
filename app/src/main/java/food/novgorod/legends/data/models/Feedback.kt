package food.novgorod.legends.data.models

import android.media.Rating

data class Feedback(
    var feedbackId: String = "",
    var isOurUser: Boolean = false,
    var userPhone: String = "", // Для наших пользователей
    var platformIcon: String = "", // Для пользователей с других платформ
    var avatarUrl: String = "",
    var username: String = "",
    var rating: Int = 0,
    var text: String = "",
    var date: String = ""
) {
}