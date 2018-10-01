package ru.s4nchez.pix4bayretrospective.di

import dagger.Module
import dagger.Provides
import ru.s4nchez.pix4bayretrospective.data.datasource.remote.APIInterface
import ru.s4nchez.pix4bayretrospective.data.entities.SearchParams
import ru.s4nchez.pix4bayretrospective.data.repositories.PhotosRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePhotosRepository(apiInterface: APIInterface):
            PhotosRepository = PhotosRepository(apiInterface)

}