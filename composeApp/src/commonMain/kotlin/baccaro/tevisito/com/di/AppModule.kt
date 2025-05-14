package baccaro.tevisito.com.di

import baccaro.tevisito.com.authentication.data.datastore.TokenDataStore
import baccaro.tevisito.com.authentication.data.remote.api.AuthApi
import baccaro.tevisito.com.authentication.data.remote.api.AuthApiImpl
import baccaro.tevisito.com.authentication.data.remote.api.ReferenceApi
import baccaro.tevisito.com.authentication.data.remote.api.ReferenceApiImpl
import baccaro.tevisito.com.authentication.domain.AuthRepository
import baccaro.tevisito.com.authentication.domain.AuthRepositoryImpl
import baccaro.tevisito.com.authentication.domain.ReferenceRepository
import baccaro.tevisito.com.authentication.domain.ReferenceRepositoryImpl
import baccaro.tevisito.com.authentication.domain.usecase.GetCategoriesUseCase
import baccaro.tevisito.com.authentication.domain.usecase.GetLocationsUseCase
import baccaro.tevisito.com.authentication.domain.usecase.LoginUseCase
import baccaro.tevisito.com.authentication.domain.usecase.RegisterClientUseCase
import baccaro.tevisito.com.authentication.domain.usecase.RegisterWorkerUseCase
import baccaro.tevisito.com.authentication.presentation.AuthViewModel
import baccaro.tevisito.com.core.KtorClientProvider
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val authModule = module {
    single { KtorClientProvider.create() }
    single<AuthApi> { AuthApiImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<ReferenceApi> { ReferenceApiImpl(get()) }
    single<ReferenceRepository> { ReferenceRepositoryImpl(get()) }
    factoryOf(::LoginUseCase)
    factoryOf(::RegisterClientUseCase)
    factoryOf(::RegisterWorkerUseCase)
    factory { GetLocationsUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    viewModelOf(::AuthViewModel)
    single { TokenDataStore() }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(authModule)
    }
}