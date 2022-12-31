package android.tvz.hr.clienttracker.di

import android.content.Context
import android.tvz.hr.clienttracker.core.util.SessionManager
import android.tvz.hr.clienttracker.onboarding.util.OnboardingPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
}