package food.novgorod.legends.data.models

import android.graphics.Color

data class Place(
    var lat: Long = 0L,
    var lng: Long = 0L,
    var color: String = "",
    var name: String = "",
    var description: String = "",
    var videoUrls: List<String> = listOf(),
    var tags: Tag = Tag(),
    var audioGuideUrl: String = "",
    var feedbacks: List<String> = listOf()
)
