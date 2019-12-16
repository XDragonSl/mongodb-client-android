package com.dd.mdbc.services

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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