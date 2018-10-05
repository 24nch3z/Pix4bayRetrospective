package ru.s4nchez.pix4bayretrospective.ui.list

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView.LayoutManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.s4nchez.pix4bayretrospective.data.entities.PAGE_SIZE
import ru.s4nchez.pix4bayretrospective.interactors.PhotosInteractor
import javax.inject.Inject

@InjectViewState
class ListPresenter @Inject constructor(
        private val interactor: PhotosInteractor
) : MvpPresenter<ContractView>(), ContractPresenter {

    private var photos = interactor.photos

    override fun init(fragment: Fragment) {
        viewState.setAdapter(photos)
        interactor.trigger.observe(fragment, Observer<Boolean> { value ->
            viewState.showHideProgressBar(false)
            if (value!!) viewState.updatePhotos()
            viewState.showHideEmptyListView(photos.isEmpty())
        })
        loadFirstPage()
    }

    private fun loadFirstPage() {
        if (photos.isEmpty()) {
            viewState.showHideProgressBar(true)
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

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
            interactor.loadNextPage()
        }
    }

    override fun search(search: String?) {
        interactor.search(search)
    }

    override fun getSearch() = interactor.getSearch()
}