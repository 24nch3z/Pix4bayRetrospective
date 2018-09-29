package ru.s4nchez.pix4bayretrospective.data.datasource.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.s4nchez.pix4bayretrospective.data.entities.Search

interface APIInterface {

    @GET("?")
    fun get(@Query("key") key: String): Call<Search>
}