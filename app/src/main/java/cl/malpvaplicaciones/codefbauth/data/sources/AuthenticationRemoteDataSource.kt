package cl.malpvaplicaciones.codefbauth.data.sources

import cl.malpvaplicaciones.codefbauth.domain.models.User
import cl.malpvaplicaciones.codefbauth.domain.utils.ResultState

interface AuthenticationRemoteDataSource {
    suspend fun register(email: String, password: String): ResultState<User>
    suspend fun login(email: String, password: String): ResultState<User>
    suspend fun logout()
    fun getCurrentUser(): User?
}