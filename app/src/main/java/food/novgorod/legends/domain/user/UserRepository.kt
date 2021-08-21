package food.novgorod.legends.domain.user

import android.content.Context
import food.novgorod.legends.data.User
import food.novgorod.legends.feature.app.App
import food.novgorod.legends.feature.welcome.WelcomeActivity

object UserRepository {
    const val prefTag = "AppData"
    const val PHONE = "phone"

    var currentUser: User? = null
    fun saveUserData(user: User) {
        val sharedPreferencesEdit = App.appContext.getSharedPreferences(
            prefTag,
            Context.MODE_PRIVATE
        ).edit()
        sharedPreferencesEdit.putString(PHONE, user.phone)
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