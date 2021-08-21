package food.novgorod.legends.feature.signup

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import food.novgorod.legends.data.LoadState
import food.novgorod.legends.data.User
import food.novgorod.legends.domain.firebase.FirebaseRoute
import food.novgorod.legends.domain.firebase.FirebaseRouteProvider
import food.novgorod.legends.domain.user.UserRepository
import food.novgorod.legends.feature.app.App
import food.novgorod.legends.feature.app.showToast
import food.novgorod.legends.feature.welcome.WelcomeActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel: ViewModel() {
    val signUpStateFlow = MutableStateFlow<LoadState>(LoadState.InProgress)
    fun saveUserData(user: User) {
        UserRepository.currentUser = user
        UserRepository.saveUserData(user)
        uploadUserToCloud(user)
    }

    private fun uploadUserToCloud(user: User) {
        FirebaseRouteProvider
            .getReference(FirebaseRoute.UserRoute(user.phone))
            .setValue(user)
    }
    fun loadUser(phone: String) {
        FirebaseRouteProvider
            .getReference(FirebaseRoute.UserRoute(phone))
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.getValue(User::class.java)
                        if(user != null) {
                            signUpStateFlow.value = LoadState.Loaded(user)
                        }
                        signUpStateFlow.value = LoadState.NotExist

                    }

                    override fun onCancelled(error: DatabaseError) {
                        showToast("Нет соединения с сервером")
                        signUpStateFlow.value = LoadState.Failed
                    }

                }
            )
    }
}