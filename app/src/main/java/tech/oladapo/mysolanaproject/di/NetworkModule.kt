package tech.oladapo.mysolanaproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.oladapo.mysolanaproject.networking.SolanaNetworkApi
import tech.oladapo.mysolanaproject.networking.SolanaNetworkImplementation


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun providesNetwork(): SolanaNetworkApi = SolanaNetworkImplementation()
}
