package cl.malpvaplicaciones.codefbauth.domain.usecases

import cl.malpvaplicaciones.codefbauth.data.repositories.AuthenticationRepository
import cl.malpvaplicaciones.codefbauth.domain.models.User
import cl.malpvaplicaciones.codefbauth.domain.utils.ResultState

class RegisterUseCase (
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): ResultState<User> {
        return when (val result = authenticationRepository.register(email, password)) {
            is ResultState.Success -> ResultState.Success(result.data)
            is ResultState.Error -> ResultState.Error(result.exception)
        }
    }

}
