package com.jeluchu.gradlebank.core.di

import com.jeluchu.gradlebank.BuildConfig
import com.jeluchu.gradlebank.core.platform.NetworkHandler
import com.jeluchu.gradlebank.features.accounts.repository.AccountsRepository
import com.jeluchu.gradlebank.features.accounts.repository.AccountsService
import com.jeluchu.gradlebank.features.accounts.usecase.GetAccounts
import com.jeluchu.gradlebank.features.accounts.view.AccountAdapter
import com.jeluchu.gradlebank.features.accounts.viewmodel.AccountsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { NetworkHandler(get()) }
    single {
        Retrofit.Builder()
            .baseUrl("http://138.68.73.118/api/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        Retrofit.Builder()
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
    }
}

val applicationModule = module(override = true) {
    factory { AccountAdapter() }
}

val datasourceModule = module {
    factory { AccountsService(get()) }
}

val repositoryModule = module {
    factory<AccountsRepository> { AccountsRepository.Network(get(), get()) }
}

val useCaseModule = module {
    factory { GetAccounts(get()) }
}

val viewModelModule = module {
    viewModel { AccountsViewModel(get()) }
}

private fun createClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}
