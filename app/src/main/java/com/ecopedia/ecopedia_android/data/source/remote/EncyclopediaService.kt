package com.ecopedia.ecopedia_android.data.source.remote

import com.ecopedia.ecopedia_android.base.BaseResponse
import com.ecopedia.ecopedia_android.data.response.HomeDataResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface EncyclopediaService {
    @GET("/book")
    suspend fun getAllItems(): ItemResponseDto

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

data class ItemResponseDto(
    val result: List<Item>
) : BaseResponse()

data class Item(
    val category: String,
    val creatureExplain: String,
    val creatureName: String,
    val idx: Int,
    val imageUrl: String,
    val location: Location
)

data class Location(
    val dong: String,
    val gu: String,
    val idx: Int,
    val si: String
)