package com.example.happyhabits.di

import android.app.Application
import com.example.happyhabits.feature_authentication.data.network.ApiHelper
import com.example.happyhabits.feature_authentication.data.network.ApiService
import com.example.happyhabits.feature_authentication.data.repository.UserRepositoryImpl
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository
import com.example.happyhabits.feature_authentication.domain.use_case.AddUser
import com.example.happyhabits.feature_authentication.domain.use_case.AuthenticateUser
import com.example.happyhabits.feature_authentication.domain.use_case.AuthenticationUseCases
import com.example.happyhabits.feature_authentication.presentation.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton
import android.content.Context
import com.example.happyhabits.R
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import okhttp3.OkHttpClient import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Create an OkHttpClient instance
        val client = OkHttpClient.Builder()
            .build()
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.65:5057/") // Replace with your actual base URL
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create()) // Handle plain text
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApiHelper(userApiService: ApiService): ApiHelper {
        return ApiHelper(userApiService);
    }
    @Provides
    @Singleton
    fun provideUserRepository(userApi: ApiHelper): IUserRepository {
        return UserRepositoryImpl(userApi)
    }
    @Provides
    @Singleton
    fun provideAuthenticationUseCases(repository: IUserRepository): AuthenticationUseCases {
        return AuthenticationUseCases(
            addUser = AddUser(repository),
            authenticateUser = AuthenticateUser(repository)
        )
    }
}
