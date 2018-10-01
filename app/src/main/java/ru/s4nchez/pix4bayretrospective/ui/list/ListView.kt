package ru.s4nchez.pix4bayretrospective.ui.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_list.*
import ru.s4nchez.pix4bayretrospective.App
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import ru.s4nchez.pix4bayretrospective.ui.common.BaseFragment
import ru.s4nchez.pix4bayretrospective.ui.fullscreen.FullscreenView
import javax.inject.Inject

class ListView : BaseFragment(), ListContract.View, PhotoAdapter.OnItemClickListener {

    override val layout = R.layout.fragment_list

    companion object {
        fun newInstance(): ListView {
            return ListView()
        }
    }

    @Inject
    lateinit var presenter: ListPresenter
    var adapter: PhotoAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        App.dagger.inject(this)
        presenter.attachView(this)
        presenter.init(this)

        recycler_view.adapter = adapter
        recycler_view.addOnScrollListener(recyclerViewOnScrollListener)
    }

    private val recyclerViewOnScrollListener =
            object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            presenter.handleOnScrollListener(recyclerView.layoutManager!!)
        }
    }

    override fun updatePhotos() {
        adapter?.updateItems()
    }

    override fun setAdapter(photos: List<Photo>) {
        adapter = PhotoAdapter(this, photos)
    }

    override fun showHideProgressBar(flag: Boolean) {
        progress_bar.visibility = if (flag) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onItemClick(item: Photo) {
        setFragment(FullscreenView.newInstance(item), true)
    }
}