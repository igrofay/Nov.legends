package food.novgorod.legends.feature.splash

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import food.novgorod.legends.R
import food.novgorod.legends.data.PlaceObject
import food.novgorod.legends.domain.place.PlaceRepository
import food.novgorod.legends.feature.app.App
import food.novgorod.legends.feature.app.showToast


class SplashViewModel: ViewModel() {
    private val mutableLiveData = MutableLiveData(0)
    init {
        downloadPlace()
    }
    val liveData get() = mutableLiveData as LiveData<Int>
    private fun downloadPlace(){
        val firebaseApp = FirebaseDatabase.getInstance()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                savePlace(snapshot)
            }
            override fun onCancelled(error: DatabaseError) {
                showToast(R.string.problem_internet)
            }

        }
        firebaseApp.reference.addListenerForSingleValueEvent(listener)
    }

    private fun savePlace(snapshot: DataSnapshot){
        for(child in snapshot.child("place").children){
            val id  = child.key.toString()
            val characteristic = child.child("characteristic").value.toString()
            val contact_details = child.child("contact_details").value.toString()
            val coor  = child.child("coor").value.toString()
            val images : List<String> = child.child("images").value.toString().split(" , ")
            val place  = child.child("place").value.toString()
            val type = child.child("type").value.toString()
            val placeObjects = PlaceObject(id , characteristic, contact_details, coor, images, place, type)
            PlaceRepository.listPlace.add(placeObjects)
            mutableLiveData.value = mutableLiveData.value?.plus(2)
        }
    }
}