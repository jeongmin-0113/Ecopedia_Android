package com.ecopedia.ecopedia_android.data.source.remote

import retrofit2.http.GET

interface SampleService {
    @GET("/test")
    suspend fun getData()
}