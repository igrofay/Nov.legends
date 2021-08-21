package food.novgorod.legends.feature.main

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import food.novgorod.legends.data.LoadState
import food.novgorod.legends.data.models.Place
import food.novgorod.legends.domain.firebase.FirebaseRoute
import food.novgorod.legends.domain.firebase.FirebaseRouteProvider
import food.novgorod.legends.feature.app.showToast
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel: ViewModel() {
    val placeLoadState = MutableStateFlow<LoadState>(LoadState.InProgress)

    fun loadPlaces() {
        FirebaseRouteProvider
            .getReference(FirebaseRoute.Places)
            .addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val places = mutableListOf<Place>()
                        snapshot.children.forEach { it.getValue(Place::class.java)?.let { places.add(it) } }
                        if(places.isEmpty()) {
                            placeLoadState.value = LoadState.NotExist
                            return
                        } else {
                            placeLoadState.value = LoadState.Loaded(places)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        showToast("Нет соединения с сервером")
                        placeLoadState.value = LoadState.Failed
                    }

                }
            )
    }
}