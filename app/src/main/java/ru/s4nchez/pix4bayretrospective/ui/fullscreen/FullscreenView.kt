package ru.s4nchez.pix4bayretrospective.ui.fullscreen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
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
import ru.s4nchez.pix4bayretrospective.extensions.hasPermission
import ru.s4nchez.pix4bayretrospective.extensions.load
import ru.s4nchez.pix4bayretrospective.extensions.toastRes
import ru.s4nchez.pix4bayretrospective.ui.common.BaseFragment
import ru.s4nchez.pix4bayretrospective.utils.isInternetConnected
import javax.inject.Inject

class FullscreenView : BaseFragment(), ContractView {

    override val layout = R.layout.fragment_fullscreen
    private val PERMISSION_REQUEST_CODE = 10

    companion object {
        val ARG_PHOTO = "ARG_PHOTO"

        fun newInstance(photo: Photo): FullscreenView {
            val fragment = FullscreenView()
            val args = Bundle()
            args.putParcelable(ARG_PHOTO, photo)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: FullscreenPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private lateinit var photo: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        App.dagger.inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        photo = arguments?.get(ARG_PHOTO) as Photo
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photo_view.load(photo.largeImageURL, fun(isSuccess: Boolean) {
            progress_bar.visibility = View.GONE
            if (!isSuccess) this.toastRes(R.string.loading_error)
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
        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            downloadImage()
        } else {
            requestPermissionWithRationale()
        }
    }

    private fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showPermissionSnackbar()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            downloadImage()
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults.isEmpty()) {
            return
        }

        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadImage()
                } else {
                    showPermissionSnackbar()
                }
            }
        }
    }

    private fun downloadImage() {
        if (!isInternetConnected(context!!)) {
            toastRes(R.string.network_disable)
            return
        }

        toastRes(R.string.screen_fullscreen_download_start);
        presenter.downloadPhoto(photo)
    }

    private fun showPermissionSnackbar() {
        Snackbar.make(activity!!.findViewById(R.id.container),
                R.string.screen_fullscreen_download_permission, Snackbar.LENGTH_LONG)
                .setAction(R.string.settings) {
                    val appSettingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + activity!!.packageName))
                    startActivityForResult(appSettingsIntent, PERMISSION_REQUEST_CODE)
                }
                .show()
    }

    override fun showToast(id: Int) {
        toastRes(id)
    }
}