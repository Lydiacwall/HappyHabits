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
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val certificateFactory = CertificateFactory.getInstance("X.509")

        // Assuming your certificate is named 'server.crt' and is located in the `res/raw` folder
        val inputStream: InputStream = context.resources.openRawResource(R.raw.localhost)
        val certificate = certificateFactory.generateCertificate(inputStream)
        inputStream.close()

        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", certificate)

        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(keyStore)

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagerFactory.trustManagers, null)

        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustManagerFactory.trustManagers[0] as javax.net.ssl.X509TrustManager)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://10.0.2.2:7033/") // Replace with your actual base URL
            .client(okHttpClient)
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
