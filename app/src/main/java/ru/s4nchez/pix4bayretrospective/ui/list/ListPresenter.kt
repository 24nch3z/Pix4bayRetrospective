package ru.s4nchez.pix4bayretrospective.ui.list

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.s4nchez.pix4bayretrospective.data.entities.Search
import ru.s4nchez.pix4bayretrospective.interactors.PhotosInteractor
import ru.s4nchez.pix4bayretrospective.ui.common.BasePresenter

class ListPresenter(
        val interactor: PhotosInteractor
) : BasePresenter<ListContract.View>(), ListContract.Presenter {

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        view?.showHideProgressBar(true)

        interactor.getPhotos().enqueue(object : Callback<Search> {
            override fun onResponse(call: Call<Search>, search: Response<Search>) {
                view?.setPhotos(search.body()?.photos!!)
                view?.showHideProgressBar(false)
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                call.cancel()
            }
        })
    }
}