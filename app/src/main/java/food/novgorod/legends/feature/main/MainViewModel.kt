package food.novgorod.legends.feature.main

import androidx.lifecycle.ViewModel
import food.novgorod.legends.R

import food.novgorod.legends.feature.app.App


class MainViewModel: ViewModel() {
    val hashMap = resursToString()

    private fun resursToString() : HashMap<String , Boolean>{
        val res = App.appContext.resources
        return hashMapOf(
            res.getString(R.string.legends) to true ,
            res.getString(R.string.tradition) to true ,
            res.getString(R.string.fairy_tales) to true  ,
            res.getString(R.string.lake) to true ,
            res.getString(R.string.source )to true ,
            res.getString(R.string.stone) to true ,
            res.getString(R.string.other) to true
        )
    }
    fun getFilter() = hashMap.filterValues { it }.keys

}