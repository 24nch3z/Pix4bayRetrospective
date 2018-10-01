package ru.s4nchez.pix4bayretrospective.ui.list

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView.LayoutManager
import ru.s4nchez.pix4bayretrospective.data.entities.Photo

interface ListContract {

    interface View {
        fun setAdapter(photos: List<Photo>)
        fun updatePhotos()
        fun showHideProgressBar(flag: Boolean)
    }

    interface Presenter {
        fun init(fragment: Fragment)
        fun loadNextPage()
        fun handleOnScrollListener(manager: LayoutManager?)
    }
}