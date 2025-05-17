package com.ecopedia.ecopedia_android.data.source.remote

import com.ecopedia.ecopedia_android.base.BaseResponse
import com.ecopedia.ecopedia_android.data.response.HomeDataResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface EncyclopediaService {
    @GET("home")
    suspend fun getHomeData()
            : HomeDataResponse

    @Multipart
    @POST("/creature/validation")
    suspend fun creatureValidation(
        @Part file: MultipartBody.Part?,
        @Part latitude: MultipartBody.Part,
        @Part longitude: MultipartBody.Part,
    ): BaseResponse

    @Multipart
    @POST("/creature/save")
    suspend fun creatureSave(
        @Part file: MultipartBody.Part?,
        @Part latitude: MultipartBody.Part,
        @Part longitude: MultipartBody.Part,
    ): BaseResponse
}


