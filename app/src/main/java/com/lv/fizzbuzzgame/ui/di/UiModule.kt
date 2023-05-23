package com.lv.fizzbuzzgame.ui.di

import com.lv.fizzbuzzgame.ui.common.dispatchers.CoroutineDispatchers
import com.lv.fizzbuzzgame.ui.common.dispatchers.CoroutineDispatchersImpl
import com.lv.fizzbuzzgame.ui.common.dispatchers.DefaultDispatcher
import com.lv.fizzbuzzgame.ui.common.dispatchers.MainDispatcher
import com.lv.fizzbuzzgame.ui.domain.usecase.ComputeGameUseCase
import com.lv.fizzbuzzgame.ui.domain.usecase.ComputeGameUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UiModule {

    @Binds
    fun bindsCoroutineDispatchers(impl: CoroutineDispatchersImpl): CoroutineDispatchers

    @Module
    @InstallIn(SingletonComponent::class)
    object Provider {
        @Provides
        @Singleton
        @MainDispatcher
        fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @Singleton
        @DefaultDispatcher
        fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @Provides
        fun provideComputeGameUseCase(): ComputeGameUseCase = ComputeGameUseCaseImpl()
    }
}
