package com.example.e_live.di

import com.example.domain.repository.ELiveRepository
import com.example.domain.usecases.sharedprefs.GetSessionSharPrefUseCase
import com.example.domain.usecases.sharedprefs.SaveSessionSharPrefUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule{

    @Provides
    fun provideSaveSessionSharPrefUseCase(recipeRepository: ELiveRepository) = SaveSessionSharPrefUseCase(recipeRepository)

    @Provides
    fun provideGetSessionSharPrefUseCase(recipeRepository: ELiveRepository) = GetSessionSharPrefUseCase(recipeRepository)

}