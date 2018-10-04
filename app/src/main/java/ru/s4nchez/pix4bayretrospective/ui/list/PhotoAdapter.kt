package ru.s4nchez.pix4bayretrospective.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.s4nchez.pix4bayretrospective.R
import ru.s4nchez.pix4bayretrospective.data.entities.Photo

class PhotoAdapter(
        val listener: OnItemClickListener,
        val photos: List<Photo>
) : RecyclerView.Adapter<PhotoHolder>() {

    fun updateItems() {
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
        return PhotoHolder(view)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photos[position], listener)
    }

    interface OnItemClickListener {
        fun onItemClick(item: Photo)
    }
}