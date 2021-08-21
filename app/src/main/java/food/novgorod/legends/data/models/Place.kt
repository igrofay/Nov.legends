package food.novgorod.legends.data.models

import android.graphics.Color

data class Place(
    var placeId: String = "null",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var color: String = "",
    var name: String = "",
    var description: String = "",
    var videoUrls: List<String> = listOf(),
    var tags: Tag = Tag(),
    var audioGuideUrl: String = "",
    var feedbacks: List<String> = listOf()
)
