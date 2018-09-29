package ru.s4nchez.pix4bayretrospective.ui.list

import ru.s4nchez.pix4bayretrospective.data.entities.Photo

interface ListContract {

    interface View {
        fun setPhotos(photos: List<Photo>)
        fun showHideProgressBar(flag: Boolean)
    }

    interface Presenter {

    }
}