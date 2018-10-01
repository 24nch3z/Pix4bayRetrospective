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

    var photos = ArrayList<Photo>()
    val trigger = MutableLiveData<Boolean>()

    fun loadPhotos() {
        val request: Call<Search> = photosRepository.loadPhotos()

        request.enqueue(object : Callback<Search> {
            override fun onResponse(call: Call<Search>, search: Response<Search>) {
                if (search.body()?.photos != null) {
                    photos.addAll(search.body()?.photos!!)
                    trigger.value = true
                }
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                call.cancel()
            }
        })
    }

}