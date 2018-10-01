package ru.s4nchez.pix4bayretrospective.ui.list

import android.support.v4.app.Fragment
import ru.s4nchez.pix4bayretrospective.data.entities.Photo

interface ListContract {

    interface View {
        fun setAdapter(photos: List<Photo>)
        fun updatePhotos()
        fun showHideProgressBar(flag: Boolean)
    }

    interface Presenter {
        fun init(fragment: Fragment)
    }
}