package ru.s4nchez.pix4bayretrospective.ui.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_list.*
import ru.s4nchez.pix4bayretrospective.App
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.datasource.remote.APIInterface
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import ru.s4nchez.pix4bayretrospective.data.entities.SearchParams
import ru.s4nchez.pix4bayretrospective.data.repositories.PhotosRepository
import ru.s4nchez.pix4bayretrospective.di.AppModule
import ru.s4nchez.pix4bayretrospective.interactors.PhotosInteractor
import ru.s4nchez.pix4bayretrospective.ui.common.BaseFragment
import ru.s4nchez.pix4bayretrospective.ui.fullscreen.FullscreenView
import ru.s4nchez.pix4bayretrospective.utils.visibilityByFlag
import javax.inject.Inject

class ListView : BaseFragment(), ContractView, PhotoAdapter.OnItemClickListener {

    override val layout = R.layout.fragment_list

    companion object {
        fun newInstance() = ListView()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: ListPresenter

    var adapter: PhotoAdapter? = null

    @ProvidePresenter
    fun providePresenter(): ListPresenter {
        return presenter
    }

    private val recyclerViewOnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    presenter.handleOnScrollListener(recyclerView.layoutManager!!)
                }
            }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


//        presenter.attachView(this)
        presenter.init(this)

        recycler_view.adapter = adapter
        recycler_view.addOnScrollListener(recyclerViewOnScrollListener)
        recycler_view.addItemDecoration(ItemDecoration(
                resources.getInteger(R.integer.recycler_view_span_count),
                resources.getDimension(R.dimen.recycler_view_spacing).toInt()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        presenter = ListPresenter(PhotosInteractor(PhotosRepository(AppModule(context!!).provideAPIInterface(AppModule(context!!).provideRetrofitClient(context!!))), SearchParams()))
        App.dagger.inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.list_view, menu)

        val searchItem = menu?.findItem(R.id.menu_item_search)
        setupSearchView(searchItem?.actionView as SearchView?)
    }

    // TODO: Скроллить в самый вверх при возврате
    private fun setupSearchView(searchView: SearchView?) {
        if (searchView == null) return

        val lastSearch = presenter.getSearch()
        if (lastSearch != null) {
            searchView.setQuery(lastSearch, false)
            searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.requestFocusFromTouch();
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                presenter.search(s)
                return true
            }

            override fun onQueryTextChange(s: String) = false
        })

        searchView.setOnQueryTextFocusChangeListener(View.OnFocusChangeListener { view, b ->
            // Если мы перестали вводить что-то в строку поиска и перед этим удалили строку,
            // выдавать результат без строки поиска
            if (!b && (view as SearchView).query.length == 0) presenter.search(null)
        })
    }

    override fun updatePhotos() {
        adapter?.updateItems()
    }

    override fun setAdapter(photos: List<Photo>) {
        adapter = PhotoAdapter(this, photos)
    }

    override fun showHideProgressBar(flag: Boolean) {
        progress_bar.visibilityByFlag(flag)
    }

    override fun showHideEmptyListView(flag: Boolean) {
        empty_list.visibilityByFlag(flag)
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        presenter.detachView()
//    }

    override fun onItemClick(item: Photo) {
        setFragment(FullscreenView.newInstance(item), true)
    }
}