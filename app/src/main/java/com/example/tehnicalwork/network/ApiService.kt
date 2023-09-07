package com.example.tehnicalwork.network

import com.example.tehnicalwork.model.HotelsData
import retrofit2.http.GET
import rx.Observable

interface ApiService {
    @GET(Const.END_URL_HOTEL_DATA)
    fun getDataHotels(): Observable<HotelsData?>?
}