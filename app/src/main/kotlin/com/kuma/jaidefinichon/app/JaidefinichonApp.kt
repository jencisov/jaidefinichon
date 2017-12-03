package com.kuma.jaidefinichon.app

import android.app.Application
import com.kuma.jaidefinichon.app.di.AppComponent
import com.kuma.jaidefinichon.app.di.DatabaseCreatorComponent
import com.kuma.jaidefinichon.data.database.DatabaseCreator

class JaidefinichonApp : Application() {

    private val component: AppComponent by lazy {
        AppComponent.Initializer.init(this)
    }

    private val databaseCreatorComponent: DatabaseCreatorComponent by lazy {
        DatabaseCreatorComponent.Initializer.init()
    }

    private val databaseCreator: DatabaseCreator by lazy {
        databaseCreatorComponent.getDatabaseCreator()
    }

    override fun onCreate() {
        super.onCreate()
        setupComponent()
        setupDatabaseCreator()

        databaseCreator.createDb(this)
    }

    private fun setupComponent() {
        component.inject(this);
    }

    private fun setupDatabaseCreator() {
        databaseCreatorComponent.inject(DatabaseCreator)
    }

    fun getAppComponent() = component

}