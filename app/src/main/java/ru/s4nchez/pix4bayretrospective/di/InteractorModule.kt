package ru.s4nchez.pix4bayretrospective.di

import dagger.Module
import dagger.Provides
import ru.s4nchez.pix4bayretrospective.data.entities.SearchParams
import ru.s4nchez.pix4bayretrospective.data.repositories.PhotosRepository
import ru.s4nchez.pix4bayretrospective.interactors.PhotosInteractor
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun providePhotosInteractor(photosRepository: PhotosRepository, searchParams: SearchParams):
            PhotosInteractor = PhotosInteractor(photosRepository, searchParams)

    @Provides
    @Singleton
    fun provideSearchParams() = SearchParams()

}