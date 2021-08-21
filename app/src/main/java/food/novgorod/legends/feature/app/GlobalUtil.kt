package food.novgorod.legends.feature.app

import android.widget.Toast


fun showToast(message: Int) {
    Toast.makeText(App.appContext, message, Toast.LENGTH_LONG).show()
}
