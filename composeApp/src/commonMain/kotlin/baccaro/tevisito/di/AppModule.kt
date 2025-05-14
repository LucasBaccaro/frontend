package baccaro.tevisito.di

import baccaro.tevisito.data.datastore.TokenDataStore
import baccaro.tevisito.data.remote.AuthApi
import baccaro.tevisito.data.remote.AuthApiImpl
import baccaro.tevisito.data.remote.KtorClientProvider
import baccaro.tevisito.data.remote.ReferenceApi
import baccaro.tevisito.data.remote.ReferenceApiImpl
import baccaro.tevisito.data.repository.AuthRepository
import baccaro.tevisito.data.repository.AuthRepositoryImpl
import baccaro.tevisito.data.repository.ReferenceRepository
import baccaro.tevisito.data.repository.ReferenceRepositoryImpl
import baccaro.tevisito.domain.usecase.GetCategoriesUseCase
import baccaro.tevisito.domain.usecase.GetLocationsUseCase
import baccaro.tevisito.domain.usecase.LoginUseCase
import baccaro.tevisito.domain.usecase.RegisterClientUseCase
import baccaro.tevisito.domain.usecase.RegisterWorkerUseCase
import baccaro.tevisito.presentation.auth.AuthViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {

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
        modules(appModule)
    }
}