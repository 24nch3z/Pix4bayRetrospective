package ru.s4nchez.pix4bayretrospective

import android.app.Application
import ru.s4nchez.pix4bayretrospective.di.*

class App: Application() {

    companion object {
        lateinit var dagger: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        dagger = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .interactorModule(InteractorModule())
                .presenterModule(PresenterModule())
                .repositoryModule(RepositoryModule())
                .build()
    }
}