package food.novgorod.legends.feature.main

import androidx.lifecycle.ViewModel
import food.novgorod.legends.R

class MainViewModel: ViewModel() {
    val hashMap = mutableMapOf(
        R.string.legends to true ,
        R.string.tradition to true ,
        R.string.fairy_tales to true  ,
        R.string.lake to true ,
        R.string.source to true ,
        R.string.stone to true ,
        R.string.other to true
    )
}