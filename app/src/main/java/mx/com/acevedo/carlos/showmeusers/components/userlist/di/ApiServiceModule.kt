package mx.com.acevedo.carlos.showmeusers.components.userlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.acevedo.carlos.showmeusers.BuildConfig
import mx.com.acevedo.carlos.showmeusers.components.userlist.api.UserServiceRepository
import mx.com.acevedo.carlos.showmeusers.components.userlist.api.UserServiceRepositoryImpl
import mx.com.acevedo.carlos.showmeusers.components.userlist.api.UsersApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Api service model, it provides [Retrofit] service instances
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.USER_API_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UsersApiService =
        retrofit.create(UsersApiService::class.java)

    @Provides
    @Singleton
    fun provideUserServiceRepository(userServiceRepository: UserServiceRepositoryImpl): UserServiceRepository =
        userServiceRepository
}