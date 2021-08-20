package food.novgorod.legends.feature.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import food.novgorod.legends.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    // TODO В этом фрагменте происходит загрузка с FB
    // и проверна на перый заход, если в sharedPreferences пусто , то мы ходим по навигации
    // если нет открываем MainActivity
}