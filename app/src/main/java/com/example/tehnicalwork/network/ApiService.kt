package com.example.tehnicalwork.network

import com.example.tehnicalwork.model.HotelsData
import com.example.tehnicalwork.model.RoomsData
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET(Const.END_URL_ROOMS_DATA)
    fun getRoomsData(): Observable<RoomsData>

    @GET(Const.END_URL_HOTELS_DATA)
    fun getHotelsData(): Observable<HotelsData>


}

