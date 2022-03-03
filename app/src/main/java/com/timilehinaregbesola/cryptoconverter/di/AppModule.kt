package com.timilehinaregbesola.cryptoconverter.di

import com.timilehinaregbesola.cryptoconverter.data.CryptoApi
import com.timilehinaregbesola.cryptoconverter.main.DefaultRepository
import com.timilehinaregbesola.cryptoconverter.main.MainRepository
import com.timilehinaregbesola.cryptoconverter.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://api.coinlayer.com/"
val appModule = module {
    fun provideCryptoApi(): CryptoApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CryptoApi::class.java)

    fun provideMainRepository(api: CryptoApi): MainRepository = DefaultRepository(api)

    single { provideCryptoApi() }
    single { provideMainRepository(get()) }
    viewModel { MainViewModel(get()) }
}
