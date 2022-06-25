package com.example.e_live.di

import android.content.Context
import com.example.data.repository.ELiveRepositoryImpl
import com.example.data.storage.sharedprefs.ELiveStorageSharPref
import com.example.data.storage.sharedprefs.SharedPrefELiveStorageImpl
import com.example.domain.repository.ELiveRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPrefELiveStorage(@ApplicationContext context: Context): ELiveStorageSharPref {
        return SharedPrefELiveStorageImpl(context = context)
    }

//    @Provides //FIXME использовать для базы данных
//    @Singleton
//    fun provideFirebaseRecipeStorage(sharedPrefRecipeStorage: RecipeStorageSharPref): RecipeStorageDB {
//        return FirebaseRecipeStorageImpl(sharedPrefRecipeStorage.getUserName())
//    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
//        recipeStorage: RecipeStorageDB,
        sharedPrefRecipeStorage: ELiveStorageSharPref
    ): ELiveRepository {
        return ELiveRepositoryImpl(
//            recipeStorage = recipeStorage
            sharPref = sharedPrefRecipeStorage
        )
    }
}