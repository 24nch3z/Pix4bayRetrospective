package ru.s4nchez.pix4bayretrospective.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_photo.view.*
import ru.s4nchez.pix4bayretrospective.GlideApp
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.entities.Photo

class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(photo: Photo, listener: PhotoAdapter.OnItemClickListener) {
        with(itemView) {
            GlideApp
                .with(context)
                .load(photo.previewURL)
                .placeholder(R.drawable.placeholder)
                .into(image)

            setOnClickListener { listener.onItemClick(photo) }
        }
    }
}