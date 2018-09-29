package ru.s4nchez.pix4bayretrospective.data.datasource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    companion object {

        private var retrofit: Retrofit? = null

        val BASE_URL = "https://pixabay.com/api/"

        fun getClient(): Retrofit? {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit
        }
    }
}