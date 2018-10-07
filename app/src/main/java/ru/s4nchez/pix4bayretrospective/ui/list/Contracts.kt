package ru.s4nchez.pix4bayretrospective.ui.list

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.MvpView
import ru.s4nchez.pix4bayretrospective.data.entities.Photo

interface ContractPresenter {
    fun init(fragment: Fragment)
    fun loadNextPage()
    fun handleOnScrollListener(manager: RecyclerView.LayoutManager?)
    fun search(search: String?)
    fun getSearch(): String?
}

interface ContractView : MvpView {
    fun setAdapter(photos: List<Photo>)
    fun updatePhotos()
    fun showHideProgressBar(flag: Boolean)
    fun showHideEmptyListView(flag: Boolean)
}