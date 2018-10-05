package ru.s4nchez.pix4bayretrospective.ui.list

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView

interface ContractPresenter {
    fun init(fragment: Fragment)
    fun loadNextPage()
    fun handleOnScrollListener(manager: RecyclerView.LayoutManager?)
    fun search(search: String?)
    fun getSearch(): String?
}