package food.novgorod.legends.feature.app

import android.app.Application
import android.content.Context
import android.widget.Toast

class App: Application() {
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}