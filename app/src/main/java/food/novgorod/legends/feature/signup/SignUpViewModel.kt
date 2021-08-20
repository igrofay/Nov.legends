package food.novgorod.legends.feature.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import food.novgorod.legends.data.User
import food.novgorod.legends.domain.user.UserRepository
import food.novgorod.legends.feature.app.App
import food.novgorod.legends.feature.welcome.WelcomeActivity

class SignUpViewModel: ViewModel() {
    fun saveUserData(user: User) {
        val sharedPreferencesEdit = App.appContext.getSharedPreferences(
            WelcomeActivity.APP_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
        sharedPreferencesEdit.putString(WelcomeActivity.KEY_FIRSTNAME, user.firstName)
        sharedPreferencesEdit.putString(WelcomeActivity.KEY_LASTNAME, user.lastName)
        sharedPreferencesEdit.putString(WelcomeActivity.KEY_IMAGE, user.imagePath)
        sharedPreferencesEdit.apply()
        UserRepository.currentUser = user
    }
}