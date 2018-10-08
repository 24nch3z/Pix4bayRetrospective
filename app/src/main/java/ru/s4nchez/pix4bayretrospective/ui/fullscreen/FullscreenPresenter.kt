package ru.s4nchez.pix4bayretrospective.ui.fullscreen

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import ru.s4nchez.pix4bayretrospective.utils.isInternetConnected
import javax.inject.Inject

@InjectViewState
class FullscreenPresenter @Inject constructor(
        private val downloadManager: DownloadManager
) : MvpPresenter<ContractView>(), ContractPresenter {

    override fun downloadPhoto(photo: Photo, context: Context?) {
        if (context == null) return

        if (!isInternetConnected(context!!)) {
            viewState.showToast(R.string.network_disable)
            return
        }

        viewState.showToast(R.string.screen_fullscreen_download_start);

        val request = DownloadManager.Request(
                Uri.parse(photo.largeImageURL))
        request.setVisibleInDownloadsUi(true)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                photo.generateFileName())
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        downloadManager.enqueue(request)
    }

    override fun onLoadImage(isSuccess: Boolean) {
        viewState.hideProgressBar()
        if (!isSuccess) viewState.showToast(R.string.loading_error)
    }
}