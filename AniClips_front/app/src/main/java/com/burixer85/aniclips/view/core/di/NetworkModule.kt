package com.burixer85.aniclips.view.core.di

import com.burixer85.aniclips.data.network.auth.ActivateAccountClient
import com.burixer85.aniclips.data.network.auth.LoginClient
import com.burixer85.aniclips.data.network.auth.RegisterClient
import com.burixer85.aniclips.data.network.home.ClipClient
import com.burixer85.aniclips.data.network.search.ThumbnailClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    val ApiBaseUrl = "http://192.168.1.141:8080/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginClient(retrofit: Retrofit): LoginClient {
        return retrofit.create(LoginClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterClient(retrofit: Retrofit): RegisterClient {
        return retrofit.create(RegisterClient::class.java)
    }

    @Singleton
    @Provides
    fun provideClipClient(retrofit: Retrofit): ClipClient {
        return retrofit.create(ClipClient::class.java)
    }

    @Singleton
    @Provides
    fun provideActivateAccountClient(retrofit: Retrofit): ActivateAccountClient {
        return retrofit.create(ActivateAccountClient::class.java)
    }

    @Singleton
    @Provides
    fun provideThumbnailClient(retrofit: Retrofit): ThumbnailClient {
        return retrofit.create(ThumbnailClient::class.java)
    }


}