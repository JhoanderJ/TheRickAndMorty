package com.jhoander.therickandmorty.di.module

import com.jhoander.therickandmorty.BuildConfig
import com.jhoander.therickandmorty.data.remote.CartoonApi
import com.jhoander.therickandmorty.data.repository.CartoonRepository
import com.jhoander.therickandmorty.data.repository.CartoonRepositoryImp
import com.jhoander.therickandmorty.utils.base.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class CartoonRepositoryModule {


    @Provides
    fun provideRepository(
        api: CartoonApi
    ): CartoonRepository {
        return CartoonRepositoryImp(api)
    }

    @Provides
    fun provideApiService(): CartoonApi {
        OkHttpClient()
        return ApiService.build(
            CartoonApi::class.java,
            BuildConfig.BaseUrl
        )
    }
}