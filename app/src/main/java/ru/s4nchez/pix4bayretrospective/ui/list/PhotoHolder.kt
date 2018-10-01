package ru.s4nchez.pix4bayretrospective.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_photo.view.*
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.entities.Photo
import ru.s4nchez.pix4bayretrospective.utils.GlideApp

class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(photo: Photo, listener: PhotoAdapter.OnItemClickListener) {
        with(itemView) {
            GlideApp
                .with(context)
                .load(photo.webformatURL)
                .placeholder(R.drawable.placeholder)
                .into(image)

            setOnClickListener { listener.onItemClick(photo) }
        }
    }
}