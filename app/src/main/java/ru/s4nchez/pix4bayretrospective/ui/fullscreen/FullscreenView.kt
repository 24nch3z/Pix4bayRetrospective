package ru.s4nchez.pix4bayretrospective.ui.fullscreen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_fullscreen.*
import ru.s4nchez.pix4bayretrospective.App
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import ru.s4nchez.pix4bayretrospective.extensions.*
import ru.s4nchez.pix4bayretrospective.ui.common.BaseFragment
import javax.inject.Inject

class FullscreenView : BaseFragment(), ContractView {

    override val layout = R.layout.fragment_fullscreen
    private val PERMISSION_REQUEST_CODE = 10
    private val DOWNLOAD_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
    private lateinit var photo: Photo

    companion object {
        val ARG_PHOTO = "ARG_PHOTO"

        fun newInstance(photo: Photo): FullscreenView = FullscreenView().apply {
            arguments = Bundle().apply { putParcelable(ARG_PHOTO, photo) }
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: FullscreenPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.dagger.inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        photo = arguments?.get(ARG_PHOTO) as Photo
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photo_view.load(photo.largeImageURL, fun(isSuccess: Boolean) {
            presenter.onLoadImage(isSuccess)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fullscreen_view, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_item_download -> onClickDownloadImage()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onClickDownloadImage() {
        if (hasPermission(DOWNLOAD_PERMISSION)) downloadImage()
        else requestPermissionWithRationale(
                R.string.screen_fullscreen_download_permission,
                DOWNLOAD_PERMISSION,
                PERMISSION_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasPermission(DOWNLOAD_PERMISSION)) {
            downloadImage()
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults.isEmpty()) return

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) downloadImage()
                else showDownloadPermissionSnackbar()
            }
        }
    }

    private fun showDownloadPermissionSnackbar() {
        showPermissionSnackbar(R.string.screen_fullscreen_download_permission, PERMISSION_REQUEST_CODE)
    }

    private fun downloadImage() = presenter.downloadPhoto(photo, context)

    override fun showToast(id: Int) = toastRes(id)

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }
}