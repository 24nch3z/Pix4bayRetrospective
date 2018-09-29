package ru.s4nchez.pix4bayretrospective.interactors

import retrofit2.Call
import ru.s4nchez.pix4bayretrospective.data.entities.Search
import ru.s4nchez.pix4bayretrospective.data.repositories.PhotosRepository

class PhotosInteractor(val photosRepository: PhotosRepository) {

    fun getPhotos(): Call<Search> = photosRepository.getPhotos()

}