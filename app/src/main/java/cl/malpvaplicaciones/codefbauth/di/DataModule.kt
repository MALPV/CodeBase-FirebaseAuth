package cl.malpvaplicaciones.codefbauth.di

import cl.malpvaplicaciones.codefbauth.data.repositories.AuthenticationRepository
import cl.malpvaplicaciones.codefbauth.data.repositories.AuthenticationRepositoryImpl
import cl.malpvaplicaciones.codefbauth.data.sources.AuthenticationRemoteDataSource
import cl.malpvaplicaciones.codefbauth.data.sources.AuthenticationRemoteDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providerAuthenticationRepository(
        authenticationRemoteDataSource: AuthenticationRemoteDataSource
    ): AuthenticationRepository = AuthenticationRepositoryImpl(authenticationRemoteDataSource)

    @Provides
    fun providerAuthenticationRemoteDateSource(
        firebaseAuth: FirebaseAuth
    ): AuthenticationRemoteDataSource = AuthenticationRemoteDataSourceImpl(firebaseAuth)

}