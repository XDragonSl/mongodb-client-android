package com.dd.mdbc.services

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .readTimeout(30, TimeUnit.SECONDS)
    .connectTimeout(30, TimeUnit.SECONDS)
    .build()
