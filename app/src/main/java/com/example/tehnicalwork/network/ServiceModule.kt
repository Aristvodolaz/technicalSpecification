package com.example.tehnicalwork.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceModule {

    private var gson: Gson? = null

    companion object {
        private var instance: ServiceModule? = null

        fun getInstance(): ServiceModule {
            if (instance == null) {
                instance = ServiceModule()
                instance?.setGson(GsonBuilder().create())
            }
            return instance!!
        }
    }

    fun getDataService(): ApiService? {
        return getDataService(Const.HOTEL_DATA, ApiService::class.java)
    }

    fun <T> getDataService(url: String?, service: Class<T>?): T {
        val okHttpClient: OkHttpClient =  OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).addInterceptor(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }).build()
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().create(service)
    }

    fun setGson(gson: Gson?) {
        this.gson = gson
    }
}