package food.novgorod.legends.feature.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class App: Application() {

    companion object {
        const val prefTag = "AppData"
        const val DARK = "isDark"
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        setTheme()
    }
    private fun setTheme(){
        val isDark = applicationContext
            .getSharedPreferences( prefTag  , Context.MODE_PRIVATE).getBoolean(DARK , false)
        AppCompatDelegate.setDefaultNightMode(
            if(isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

    }
}