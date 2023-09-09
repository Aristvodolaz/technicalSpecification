package com.example.tehnicalwork.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tehnicalwork.model.HotelsData
import com.example.tehnicalwork.model.RoomsData
import com.example.tehnicalwork.network.ApiRepository
import rx.Observable
class HotelViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    fun getRoomsData(): Observable<RoomsData> {
        return apiRepository.getRoomsData()
    }

    fun getHotelsData(): Observable<HotelsData> {
        return apiRepository.getHotelsData()
    }
}
