package ru.s4nchez.pix4bayretrospective.data.datasource.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.s4nchez.pix4bayretrospective.data.entities.Search

interface APIInterface {

    @GET("?")
    fun get(
            @Query("key") key: String,
            @Query("q") search: String?,
            @Query("per_page") perPage: Int,
            @Query("page") page: Int,
            @Query("safesearch") safeSearch: Boolean
    ): Call<Search>
}