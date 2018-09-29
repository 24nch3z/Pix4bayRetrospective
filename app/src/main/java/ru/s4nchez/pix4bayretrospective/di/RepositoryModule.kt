package ru.s4nchez.pix4bayretrospective.di

import dagger.Module
import dagger.Provides
import ru.s4nchez.pix4bayretrospective.data.repositories.PhotosRepository
import ru.s4nchez.pix4bayretrospective.data.repositories.SearchParamsRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePhotosRepository(): PhotosRepository = PhotosRepository()

    @Provides
    @Singleton
    fun provideSearchParamsRepository(): SearchParamsRepository = SearchParamsRepository()
}