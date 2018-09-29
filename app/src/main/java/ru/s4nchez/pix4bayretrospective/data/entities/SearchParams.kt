package ru.s4nchez.pix4bayretrospective.data.entities

import com.google.gson.annotations.SerializedName

class SearchParams {
    @SerializedName("q") var q: String = ""
    @SerializedName("per_page") val perPage: Int = 90
    @SerializedName("page") var page: Int = 1
}