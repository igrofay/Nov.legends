package food.novgorod.legends

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import food.novgorod.legends.data.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    companion object{
        const val KEY_USER = "KEY_USER==KEY_USER"
        fun intentMainActivity(context: Context , user: User) = Intent(context , MainActivity::class.java).apply{
            putExtra(KEY_USER , user)
        }
    }
}