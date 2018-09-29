package ru.s4nchez.pix4bayretrospective.di

import dagger.Module
import dagger.Provides
import ru.s4nchez.pix4bayretrospective.interactors.PhotosInteractor
import ru.s4nchez.pix4bayretrospective.ui.list.ListPresenter

@Module
class PresenterModule {

    @Provides
    fun provideListPresenter(photosInteractor: PhotosInteractor):
            ListPresenter = ListPresenter(photosInteractor)

}