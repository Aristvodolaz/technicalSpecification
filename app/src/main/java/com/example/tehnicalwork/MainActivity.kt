package com.example.tehnicalwork

import HotelViewModelFactory
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tehnicalwork.model.HotelsData
import com.example.tehnicalwork.model.RoomsData
import com.example.tehnicalwork.network.ApiRepository
import com.example.tehnicalwork.network.ApiService
import com.example.tehnicalwork.network.ServiceModule
import com.example.tehnicalwork.viewModel.HotelViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var address: TextView
    private lateinit var nameHotel: TextView
    private lateinit var priceHotel:TextView
    private lateinit var turInfo: TextView


    private lateinit var viewModel: HotelViewModel
    private lateinit var apiService: ApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hotel_layout)

        bindingFunction()

        apiService = ServiceModule.getInstance().getDataService()!!
        val apiRepository = ApiRepository(apiService)

        viewModel = ViewModelProvider(this, HotelViewModelFactory(apiRepository))[HotelViewModel::class.java]

        viewModel.getHotelsData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ hotelsData ->
                displayRoomsData(hotelsData)

            }, { error ->
                showError(error.message ?: "Unknown error", applicationContext)
            })

        viewModel.getHotelsData()
    }

    private fun bindingFunction() {
        address = findViewById(R.id.address_hotel)
        nameHotel = findViewById(R.id.name_hotel)
        priceHotel = findViewById(R.id.price_hotel)
        turInfo = findViewById(R.id.travel_info)
    }


    fun displayRoomsData(data: HotelsData) {

        nameHotel.text = data.name
        address.text = data.adress
        priceHotel.text = "от "+data.minimal_price
        turInfo.text = data.price_for_it
    }

    fun showError(errorMessage: String, context: Context) {
        Log.v("YEE", errorMessage);
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
