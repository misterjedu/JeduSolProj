package tech.oladapo.mysolanaproject.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(
    ViewModelComponent::class
)

@Module
class SolanaProjectModule {

    @Provides
    fun providesSharedPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("scaffold_prefs", Context.MODE_PRIVATE)
    }
}