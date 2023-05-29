package android.tvz.hr.clienttracker.di

import android.content.Context
import android.tvz.hr.clienttracker.client_details.data.ClientDetailsRepositoryImp
import android.tvz.hr.clienttracker.client_details.domain.repository.ClientDetailsRepository
import android.tvz.hr.clienttracker.clients_list.data.repository.ClientRepositoryImplementation
import android.tvz.hr.clienttracker.clients_list.domain.repository.ClientsRepository
import android.tvz.hr.clienttracker.common.util.SessionManager
import android.tvz.hr.clienttracker.data.local.database.ClientDatabase
import android.tvz.hr.clienttracker.data.local.entities.ClientDao
import android.tvz.hr.clienttracker.data.remote.ClientTrackerApi
import android.tvz.hr.clienttracker.login_user.data.repository.UserLoginRepositoryImplementation
import android.tvz.hr.clienttracker.login_user.domain.repository.UserLoginRepository
import android.tvz.hr.clienttracker.onboarding.util.OnboardingPrefs
import android.tvz.hr.clienttracker.user_registration.data.repository.UserRepositoryImplementation
import android.tvz.hr.clienttracker.user_registration.domain.repository.UserRepository
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideOnboardingPrefs(
        @ApplicationContext context: Context
    ) = OnboardingPrefs(context = context)

    @Singleton
    @Provides
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = SessionManager(context)

    @Singleton
    @Provides
    fun provideClientApi(): ClientTrackerApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClientTrackerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        clientTrackerApi: ClientTrackerApi,
        sessionManager: SessionManager
    ): UserRepository {
        return UserRepositoryImplementation(
            clientTrackerApi,
            sessionManager
        )
    }

    @Singleton
    @Provides
    fun provideClientRepository(
        clientTrackerApi: ClientTrackerApi,
        clientDao: ClientDao
    ): ClientsRepository {
        return ClientRepositoryImplementation(
            clientTrackerApi,
            clientDao
        )
    }

    @Singleton
    @Provides
    fun provideClientDetailsRepository(
        clientDao: ClientDao,
    ): ClientDetailsRepository {
        return ClientDetailsRepositoryImp(
            clientDao,
        )
    }

    @Singleton
    @Provides
    fun provideUserLoginRepository(
        clientTrackerApi: ClientTrackerApi,
        sessionManager: SessionManager
    ): UserLoginRepository {
        return UserLoginRepositoryImplementation(
            clientTrackerApi,
            sessionManager
        )
    }
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ClientDatabase::class.java,
        CLIENT_DATABASE
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideClientDao(clientDatabase: ClientDatabase) = clientDatabase.clientDao()
}

const val BASE_URL = "http://192.168.1.4:8080/"
private const val CLIENT_DATABASE = "ClientDatabase"