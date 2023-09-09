package com.example.tehnicalwork.network

import com.example.tehnicalwork.model.HotelsData
import com.example.tehnicalwork.model.RoomsData
import rx.Observable

class ApiRepository(private val apiService: ApiService) {
    fun getRoomsData(): Observable<RoomsData> {
        return apiService.getRoomsData()
    }

    fun getHotelsData(): Observable<HotelsData> {
        return apiService.getHotelsData()
    }
}
