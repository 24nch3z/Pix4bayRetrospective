package ru.s4nchez.pix4bayretrospective.data.repositories

import retrofit2.Call
import ru.s4nchez.pix4bayretrospective.API_KEY
import ru.s4nchez.pix4bayretrospective.data.datasource.remote.APIInterface
import ru.s4nchez.pix4bayretrospective.data.entities.Search

class PhotosRepository(var apiInterface: APIInterface) {

    fun loadPhotos(): Call<Search> = apiInterface.get(API_KEY)

}