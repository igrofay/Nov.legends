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
    fun getUserData(): Boolean{
        val sharedPreferences = App.appContext.getSharedPreferences(
            prefTag,
            Context.MODE_PRIVATE
        )
        val phone = sharedPreferences.getString(PHONE , null)
        val name = sharedPreferences.getString(NAME , null)
        val image = sharedPreferences.getString(IMAGEPATH , "")
        if (phone == null && name == null) return false
        currentUser = User(name.toString() , phone.toString() , image.toString())
        return true
    }
    
}