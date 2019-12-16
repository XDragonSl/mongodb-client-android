package com.dd.mdbc.services

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DocumentService {

    @FormUrlEncoded
    @POST("documents")
    fun load(@Field("uri") uri: String,
             @Field("collection") collection: String): Observable<List<Any>>

    companion object Factory {
        fun create(): DocumentService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://mongodb-client-api.herokuapp.com/")
                .build()

            return retrofit.create(DocumentService::class.java);
        }
    }
}