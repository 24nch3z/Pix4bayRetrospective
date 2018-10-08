package ru.s4nchez.pix4bayretrospective.ui.fullscreen

import android.content.Context
import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import ru.s4nchez.pix4bayretrospective.data.entities.Photo

interface ContractPresenter {
    fun downloadPhoto(photo: Photo, context: Context?)
    fun onLoadImage(isSuccess: Boolean)
}

interface ContractView : MvpView {
    fun showToast(@StringRes id: Int)
    fun hideProgressBar()
}