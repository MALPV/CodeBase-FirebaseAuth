package cl.malpvaplicaciones.codefbauth.di

import cl.malpvaplicaciones.codefbauth.data.repositories.AuthenticationRepository
import cl.malpvaplicaciones.codefbauth.domain.usecases.LoginUseCase
import cl.malpvaplicaciones.codefbauth.domain.usecases.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun providerLoginUseCase(
        authenticationRepository: AuthenticationRepository
    ) = LoginUseCase(authenticationRepository)

    @Provides
    @ViewModelScoped
    fun providerRegisterUseCase(
        authenticationRepository: AuthenticationRepository
    ) = RegisterUseCase(authenticationRepository)

}