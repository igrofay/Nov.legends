package food.novgorod.legends.data

sealed class LoadState {
    object InProgress: LoadState()
    object Failed: LoadState()
    object NotExist: LoadState()
    data class Loaded(val result: Any): LoadState()
}