package com.ecopedia.ecopedia_android.di


import com.ecopedia.ecopedia_android.data.source.local.TokenManager
import com.ecopedia.ecopedia_android.data.source.remote.RequestInterceptor
import com.ecopedia.ecopedia_android.data.source.remote.SampleService
import com.ecopedia.ecopedia_android.data.source.remote.UserService
import com.ecopedia.ecopedia_android.utils.Constant.BASE_URL
import com.ecopedia.ecopedia_android.utils.Constant.CONNECT_TIME_OUT
import com.ecopedia.ecopedia_android.utils.Constant.READ_TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder().readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
        .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
        .addInterceptor(httpLoggingInterceptor).addInterceptor(requestInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRequestInterceptor(tokenManager: TokenManager): Interceptor =
        RequestInterceptor(tokenManager)


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideAlarmApiService(retrofit: Retrofit): SampleService {
        return retrofit.create(SampleService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}