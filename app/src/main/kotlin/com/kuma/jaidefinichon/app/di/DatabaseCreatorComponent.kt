package com.kuma.jaidefinichon.app.di

import com.kuma.jaidefinichon.data.database.DatabaseCreator
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(DatabaseCreatorModule::class))
interface DatabaseCreatorComponent {

    fun inject(databaseCreator: DatabaseCreator)

    fun getDatabaseCreator(): DatabaseCreator

    object Initializer {
        fun init(): DatabaseCreatorComponent =
                DaggerDatabaseCreatorComponent
                        .builder()
                        .databaseCreatorModule(DatabaseCreatorModule())
                        .build()
    }

}