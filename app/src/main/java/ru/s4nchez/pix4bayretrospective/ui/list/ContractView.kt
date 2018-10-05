package ru.s4nchez.pix4bayretrospective.ui.list

import com.arellomobile.mvp.MvpView
import ru.s4nchez.pix4bayretrospective.data.entities.Photo

interface ContractView : MvpView {
    fun setAdapter(photos: List<Photo>)
    fun updatePhotos()
    fun showHideProgressBar(flag: Boolean)
    fun showHideEmptyListView(flag: Boolean)
}