package com.kuma.jaidefinichon.app.di

import com.kuma.jaidefinichon.app.JaidefinichonApp
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(AppModule::class)) interface AppComponent {

    fun inject(app: JaidefinichonApp)

    object Initializer {
        fun init(app: JaidefinichonApp): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule(app))
                        .build()
    }

}