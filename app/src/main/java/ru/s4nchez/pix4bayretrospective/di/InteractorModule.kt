package ru.s4nchez.pix4bayretrospective.di

import dagger.Module
import dagger.Provides
import ru.s4nchez.pix4bayretrospective.data.repositories.PhotosRepository
import ru.s4nchez.pix4bayretrospective.interactors.PhotosInteractor

@Module
class InteractorModule {

    @Provides
    fun providePhotosInteractor(photosRepository: PhotosRepository):
            PhotosInteractor = PhotosInteractor(photosRepository)

}