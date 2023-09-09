package com.example.tehnicalwork.network

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BaseDataProvider<T> {
    protected var schedulerTransformer: Observable.Transformer<T, T>? = null
    protected var serviceData: ApiService? = ServiceModule.getInstance().getDataService()

    init {
        schedulerTransformer = Observable.Transformer<T, T> { o: Observable<T> ->
            o
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
        }
    }

    protected fun applySchedulers(): Observable.Transformer<T, T>? {
        return schedulerTransformer
    }

}
