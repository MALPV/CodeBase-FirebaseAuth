package cl.malpvaplicaciones.codefbauth.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.malpvaplicaciones.codefbauth.domain.models.User
import cl.malpvaplicaciones.codefbauth.domain.usecases.LoginUseCase
import cl.malpvaplicaciones.codefbauth.domain.usecases.RegisterUseCase
import cl.malpvaplicaciones.codefbauth.domain.utils.ResultState
import cl.malpvaplicaciones.codefbauth.presentation.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<ScreenState<User>>()
    val loginState: LiveData<ScreenState<User>> = _loginState

    private val _registerState = MutableLiveData<ScreenState<Unit>>()
    val registerState: LiveData<ScreenState<Unit>> = _registerState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = ScreenState.Loading()

            when(val result = loginUseCase(email, password)){
                is ResultState.Success -> _loginState.value = ScreenState.Success(result.data)
                is ResultState.Error -> _loginState.value = ScreenState.Error(result.exception)
            }


        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = ScreenState.Loading()

            when(val result = registerUseCase(email, password)){
                is ResultState.Success -> _loginState.value = ScreenState.Success(result.data)
                is ResultState.Error -> _loginState.value = ScreenState.Error(result.exception)
            }
        }
    }
}