package ru.s4nchez.pix4bayretrospective.interactors

import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import ru.s4nchez.pix4bayretrospective.data.entities.Search
import ru.s4nchez.pix4bayretrospective.data.entities.SearchParams
import ru.s4nchez.pix4bayretrospective.data.repositories.PhotosRepository

class PhotosInteractor(
        val photosRepository: PhotosRepository,
        val searchParams: SearchParams
) {

    val trigger = MutableLiveData<Boolean>()

    var photos = ArrayList<Photo>()
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    private fun loadPhotos(isFirstPage: Boolean) {
        isLoading = true
        val request: Call<Search> = photosRepository.loadPhotos(searchParams)

        request.enqueue(object : Callback<Search> {
            override fun onResponse(call: Call<Search>, search: Response<Search>) {

                if (search.body()?.photos != null) {
                    if (isFirstPage) photos.clear()
                    photos.addAll(search.body()?.photos!!)
                    trigger.value = true
                } else isLastPage = true

                isLoading = false
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun loadFirstPage() {
        if (isLoading) return

        searchParams.page = 1
        isLastPage = false
        loadPhotos(true)
    }

    fun loadNextPage() {
        if (isLoading || isLastPage) return

        searchParams.page += 1
        loadPhotos(false)
    }
}