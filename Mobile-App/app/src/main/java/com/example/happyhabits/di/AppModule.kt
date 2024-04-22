package com.example.happyhabits.di

import android.app.Application
import com.example.happyhabits.feature_authentication.data.data_source.UserDao
import com.example.happyhabits.feature_authentication.data.data_source.UserDatabase
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserDao(): UserDao {
        // Create and return your UserDao instance here
        return UserDao()
    }
    @Provides
    @Singleton
    fun provideNoteDatabase(userDao: UserDao): UserDatabase {
        return UserDatabase(userDao);
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db: UserDatabase): IUserRepository {
        return UserRepositoryImpl(db.userDao)
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
