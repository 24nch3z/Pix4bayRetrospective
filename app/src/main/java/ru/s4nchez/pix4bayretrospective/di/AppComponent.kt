package ru.s4nchez.pix4bayretrospective.di

import dagger.Component
import ru.s4nchez.pix4bayretrospective.ui.list.ListView
import javax.inject.Singleton

@Component(
        modules = [
            InteractorModule::class,
            PresenterModule::class,
            RepositoryModule::class
        ]
)
@Singleton
interface AppComponent {
    fun inject(view: ListView);
}