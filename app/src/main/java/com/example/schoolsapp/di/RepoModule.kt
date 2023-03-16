package com.example.schoolsapp.di

import com.example.schoolsapp.repository.RetroApiService
import com.example.schoolsapp.repository.SchoolRepository
import com.example.schoolsapp.repository.SchoolRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Singleton
    @Provides
    fun providesSchoolRepository(retroAPI: RetroApiService): SchoolRepository {
        return SchoolRepositoryImp(retroAPI)
    }
}