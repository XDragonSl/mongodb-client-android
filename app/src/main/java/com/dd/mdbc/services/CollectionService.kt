package com.dd.mdbc.services

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .readTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()

interface CollectionService {

    @FormUrlEncoded
    @POST("collections")
    fun load(@Field("uri") uri: String): Observable<List<String>>

    companion object Factory {
        fun create(): CollectionService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://mongodb-client-api.herokuapp.com/")
                .build()

            return retrofit.create(CollectionService::class.java);
        }
    }
}