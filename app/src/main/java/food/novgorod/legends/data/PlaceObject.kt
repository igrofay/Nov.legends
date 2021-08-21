package food.novgorod.legends.data

data class PlaceObject(
    val id : String ,
    val characteristic : String,
    val contact_details : String,
    val coor : String ,
    val images : List<String>,
    val place : String ,
    val type : String
)
