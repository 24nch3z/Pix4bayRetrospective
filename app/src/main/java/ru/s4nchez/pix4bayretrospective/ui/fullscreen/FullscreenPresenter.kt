package ru.s4nchez.pix4bayretrospective.ui.fullscreen

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import javax.inject.Inject

@InjectViewState
class FullscreenPresenter @Inject constructor(
        private val downloadManager: DownloadManager
) : MvpPresenter<ContractView>(), ContractPresenter {

    override fun downloadPhoto(photo: Photo) {
        val request = DownloadManager.Request(
                Uri.parse(photo.largeImageURL))
        request.setVisibleInDownloadsUi(true)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                photo.generateFileName())
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        downloadManager.enqueue(request)
    }
}