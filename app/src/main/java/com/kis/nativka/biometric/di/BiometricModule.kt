package com.kis.nativka.biometric.di

import androidx.fragment.app.FragmentActivity
import com.kis.nativka.biometric.data.repository.BiometricRepositoryImpl
import com.kis.nativka.biometric.domain.repository.BiometricRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BiometricModule {
    @Binds
    abstract fun bindBiometricRepository(
        impl: BiometricRepositoryImpl
    ): BiometricRepository
}