package com.example.dalelarsen.todolist

import android.app.Application
import io.realm.Realm

/**
 * Created by dalelarsen on 1/24/18.
 */

open class ToDoListApplication : Application() {
    public override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}