package ru.s4nchez.pix4bayretrospective.data.entities

val PAGE_SIZE = 96

class SearchParams {
    var search: String? = null
    val perPage: Int = PAGE_SIZE
    var page: Int = 1
}