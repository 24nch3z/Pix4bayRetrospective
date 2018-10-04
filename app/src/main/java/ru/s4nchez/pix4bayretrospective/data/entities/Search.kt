package ru.s4nchez.pix4bayretrospective.data.entities

import com.google.gson.annotations.SerializedName

data class Search(
        @SerializedName("total") var total: Int,
        @SerializedName("totalHits") var totalHits: Int,
        @SerializedName("hits") var photos: List<Photo>
)