package food.novgorod.legends.feature.signup

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import food.novgorod.legends.data.LoadState
import food.novgorod.legends.data.User
import food.novgorod.legends.domain.user.UserRepository
import food.novgorod.legends.feature.app.App
import food.novgorod.legends.feature.app.showToast
import food.novgorod.legends.feature.welcome.WelcomeActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel: ViewModel() {
    fun saveUserData(user: User) {
        UserRepository.currentUser = user
        UserRepository.saveUserData(user)
    }


}