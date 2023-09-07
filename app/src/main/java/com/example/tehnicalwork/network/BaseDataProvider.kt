package com.example.tehnicalwork.network

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BaseDataProvider {
    protected var schedulerTransformer: Observable.Transformer<*, *>? = null
    protected var serviceData: ApiService? = ServiceModule.getInstance().getDataService()

    fun BaseDataProvider() {
        schedulerTransformer =
            Observable.Transformer<String, String> { o: Observable<String> ->
                o
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
            }
    }

    protected fun <T> applySchedulers(): Observable.Transformer<T, T>? {
        return schedulerTransformer as Observable.Transformer<T, T>?
    }
}