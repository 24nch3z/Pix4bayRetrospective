package ru.s4nchez.pix4bayretrospective.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.s4nchez.pix4bayretrospective.data.datasource.remote.APIInterface
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    val BASE_URL = "https://pixabay.com/api/"

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideRetrofitClient(context: Context): Retrofit {
        val cacheSize: Long = 10 * 1024 * 1024 // 10MB
        val cache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .build()

        var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit
    }

    @Provides
    @Singleton
    fun provideAPIInterface(retrofit: Retrofit): APIInterface {
        return retrofit.create(APIInterface::class.java)
    }
}