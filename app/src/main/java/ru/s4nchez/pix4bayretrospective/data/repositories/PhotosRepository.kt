package ru.s4nchez.pix4bayretrospective.data.repositories

import retrofit2.Call
import ru.s4nchez.pix4bayretrospective.data.datasource.remote.APIInterface
import ru.s4nchez.pix4bayretrospective.data.entities.Search
import ru.s4nchez.pix4bayretrospective.data.entities.SearchParams

class PhotosRepository(
        var apiInterface: APIInterface,
        var searchParams: SearchParams
) {

    private val API_KEY = "3486658-7652a9c35c17fe1501b627149"

    fun getPhotos(): Call<Search> = apiInterface.get(API_KEY)

}