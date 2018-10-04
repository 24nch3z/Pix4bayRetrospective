package ru.s4nchez.pix4bayretrospective.ui.common

abstract class BasePresenter<T> {

    protected var view: T? = null

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun hasWin(): Boolean {
        return view != null
    }
}
