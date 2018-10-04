package ru.s4nchez.pix4bayretrospective.ui.fullscreen

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_fullscreen.*
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import ru.s4nchez.pix4bayretrospective.ui.common.BaseFragment
import ru.s4nchez.pix4bayretrospective.utils.load
import ru.s4nchez.pix4bayretrospective.utils.shortResToast

class FullscreenView : BaseFragment(), FullscreenContract.View {

    override val layout = R.layout.fragment_fullscreen

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

    private var photo: Photo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photo = arguments?.get(ARG_PHOTO) as Photo
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        photo_view.load(photo?.largeImageURL!!, fun(isSuccess: Boolean) {
            progress_bar.visibility = View.GONE
            if (!isSuccess) this.shortResToast(R.string.loading_error)
        })
    }
}