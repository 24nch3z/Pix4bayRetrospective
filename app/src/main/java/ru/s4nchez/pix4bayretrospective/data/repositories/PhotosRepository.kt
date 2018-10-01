package ru.s4nchez.pix4bayretrospective.data.repositories

import retrofit2.Call
import ru.s4nchez.pix4bayretrospective.API_KEY
import ru.s4nchez.pix4bayretrospective.data.datasource.remote.APIInterface
import ru.s4nchez.pix4bayretrospective.data.entities.Search
import ru.s4nchez.pix4bayretrospective.data.entities.SearchParams

class PhotosRepository(var apiInterface: APIInterface) {

    fun loadPhotos(searchParams: SearchParams): Call<Search> {
        with(searchParams) {
            return apiInterface.get(
                    key = API_KEY,
                    search = search,
                    perPage = perPage,
                    page = page
            )
        }
    }

}