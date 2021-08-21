package food.novgorod.legends.domain.user

import android.content.Context
import food.novgorod.legends.data.User
import food.novgorod.legends.feature.app.App

object UserRepository {
    const val prefTag = "AppData"
    const val PHONE = "phone"
    const val NAME = "name"
    const val IMAGEPATH = "pathImage"
    var currentUser: User? = null
    fun saveUserData(user: User) {
        val sharedPreferencesEdit = App.appContext.getSharedPreferences(
            prefTag,
            Context.MODE_PRIVATE
        ).edit()
        sharedPreferencesEdit.putString(PHONE, user.phone)
        sharedPreferencesEdit.putString(NAME , user.name)
        sharedPreferencesEdit.putString(IMAGEPATH , user.imagePath)
        sharedPreferencesEdit.apply()
    }
    fun loadUserPhone(): String? {
        val sharedPreferences = App.appContext.getSharedPreferences(
            prefTag,
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(PHONE, null)
    }
}