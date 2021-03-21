package com.example.contacts.data

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import androidx.lifecycle.MutableLiveData

abstract class ContentProviderLiveData<T>(
    private val context: Context,
    val uri: Uri
) : MutableLiveData<T>(){

    private lateinit var observer: ContentObserver

    override fun onInactive() {
        context.contentResolver.unregisterContentObserver(observer)
    }

    override fun onActive() {

        postValue(getContentProviderValue())
        observer = object : ContentObserver(null){

            override fun onChange(selfChange: Boolean) {
                postValue(getContentProviderValue())
            }
        }
    }

    abstract fun getContentProviderValue(): T
}