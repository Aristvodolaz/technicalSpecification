package com.example.tehnicalwork.model

data class RoomsData(
    var id: Int,
    var name: String,
    var price: Int,
    var price_per: String,
    var peculiarities: List<String>,
    var image_urls: List<String>
)
