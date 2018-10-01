package ru.s4nchez.pix4bayretrospective.ui.list

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import ru.s4nchez.pix4bayretrospective.interactors.PhotosInteractor
import ru.s4nchez.pix4bayretrospective.ui.common.BasePresenter

class ListPresenter(
        val interactor: PhotosInteractor
) : BasePresenter<ListContract.View>(), ListContract.Presenter {

    var photos = interactor.photos

    override fun init(fragment: Fragment) {
        view?.setAdapter(photos)
        interactor.trigger.observe(fragment, Observer<Boolean> { v ->
            view?.showHideProgressBar(false)
            view?.updatePhotos()
        })
        loadPhotos()
    }

    private fun loadPhotos() {
        view?.showHideProgressBar(true)
        interactor.loadPhotos()
    }
}