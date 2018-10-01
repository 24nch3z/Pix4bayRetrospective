package ru.s4nchez.pix4bayretrospective.ui.list

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView.LayoutManager
import ru.s4nchez.pix4bayretrospective.data.entities.PAGE_SIZE
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
        loadFirstPage()
    }

    private fun loadFirstPage() {
        if (photos.isEmpty()) {
            view?.showHideProgressBar(true)
            interactor.loadFirstPage()
        }
    }

    override fun loadNextPage() {
        interactor.loadNextPage()
    }

    override fun handleOnScrollListener(manager: LayoutManager?) {
        if (manager == null) return

        val visibleItemCount = manager.childCount
        val totalItemCount = manager.itemCount
        val firstVisibleItemPosition = (manager as GridLayoutManager)
                .findFirstVisibleItemPosition()

        if (!interactor.isLoading &&
                !interactor.isLastPage &&
                visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {

            interactor.loadNextPage()
        }
    }
}