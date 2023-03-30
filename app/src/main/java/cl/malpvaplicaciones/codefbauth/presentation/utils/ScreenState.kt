package cl.malpvaplicaciones.codefbauth.presentation.utils

sealed class ScreenState<T> {
    class Loading<T> : ScreenState<T>()
    data class Success<T>(val data: T) : ScreenState<T>()
    data class Error<T>(val exception: Exception) : ScreenState<T>()
}