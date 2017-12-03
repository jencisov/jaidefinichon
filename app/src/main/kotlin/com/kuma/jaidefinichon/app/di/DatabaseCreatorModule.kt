package com.kuma.jaidefinichon.app.di

import com.kuma.jaidefinichon.data.database.DatabaseCreator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class DatabaseCreatorModule {

    @Provides @Singleton fun provideDatabaseCreator(): DatabaseCreator = DatabaseCreator

}