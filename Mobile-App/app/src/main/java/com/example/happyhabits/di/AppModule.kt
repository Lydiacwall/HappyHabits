package com.example.happyhabits.di

import android.app.Application
import com.example.happyhabits.feature_authentication.data.data_source.UserDao
import com.example.happyhabits.feature_authentication.data.data_source.UserDatabase
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun provideUserDao(): UserDao {
//        // Create and return your UserDao instance here
//        return UserDao()
//    }
//    @Provides
//    @Singleton
//    fun provideUserDatabase(userDao: UserDao): UserDatabase {
//        return UserDatabase(userDao);
//    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5057/") // Replace with your actual base URL
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
