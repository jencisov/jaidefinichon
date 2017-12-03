package com.kuma.jaidefinichon.app.di

import com.kuma.jaidefinichon.app.JaidefinichonApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(private val app: JaidefinichonApp) {
    @Provides @Singleton fun provideApp() = app
    @Provides @Singleton fun provideContext() = app
}