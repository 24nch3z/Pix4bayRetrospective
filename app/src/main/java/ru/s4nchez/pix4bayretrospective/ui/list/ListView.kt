package ru.s4nchez.pix4bayretrospective.ui.list

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_list.*
import ru.s4nchez.pix4bayretrospective.App
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import ru.s4nchez.pix4bayretrospective.ui.common.BaseFragment
import javax.inject.Inject

class ListView : BaseFragment(), ListContract.View {

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
    }

    override fun setPhotos(photos: List<Photo>) {
        if (adapter == null) {
            adapter = PhotoAdapter()
            recycler_view.adapter = adapter
        }
        adapter?.updateItems(photos)
    }

    override fun showHideProgressBar(flag: Boolean) {
        progress_bar.visibility = if (flag) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}