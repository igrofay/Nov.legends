package food.novgorod.legends.feature.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import food.novgorod.legends.data.LoadState
import food.novgorod.legends.data.User
import food.novgorod.legends.domain.firebase.FirebaseRoute
import food.novgorod.legends.domain.firebase.FirebaseRouteProvider
import food.novgorod.legends.domain.user.UserRepository
import food.novgorod.legends.feature.app.showToast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel: ViewModel() {

    fun loadUser(): StateFlow<LoadState> {
        val resStateFlow = MutableStateFlow<LoadState>(LoadState.InProgress)
        val phone = UserRepository.loadUserPhone()
        if(phone == null) {
            resStateFlow.value = LoadState.NotExist
        } else {
            FirebaseRouteProvider.getReference(FirebaseRoute.UserRoute(phone))
                .addListenerForSingleValueEvent(
                    object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user = snapshot.getValue(User::class.java)
                            if(user == null ) {
                                resStateFlow.value = LoadState.NotExist
                                return
                            }
                            UserRepository.currentUser = user
                            resStateFlow.value = LoadState.Loaded(user)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            showToast("Нет соединения с сервером")
                            resStateFlow.value = LoadState.Failed
                        }

                    }
                )
        }
        return resStateFlow
    }
}