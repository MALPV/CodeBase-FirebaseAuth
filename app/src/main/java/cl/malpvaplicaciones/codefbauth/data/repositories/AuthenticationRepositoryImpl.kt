package cl.malpvaplicaciones.codefbauth.data.repositories

import cl.malpvaplicaciones.codefbauth.data.sources.AuthenticationRemoteDataSource
import cl.malpvaplicaciones.codefbauth.domain.models.User
import cl.malpvaplicaciones.codefbauth.domain.utils.ResultState

class AuthenticationRepositoryImpl (
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {

    override suspend fun register(email: String, password: String): ResultState<User> =
        remoteDataSource.register(email, password)

    override suspend fun login(email: String, password: String): ResultState<User> =
        remoteDataSource.login(email, password)

    override suspend fun logout() = remoteDataSource.logout()

    override fun getCurrentUser(): User? = remoteDataSource.getCurrentUser()

}
