package com.ecopedia.ecopedia_android.data.source.remote

import com.ecopedia.ecopedia_android.base.BaseResponse
import retrofit2.http.GET

interface EncyclopediaService {
    @GET("/book")
    suspend fun getAllItems(): ItemResponseDto

    // 생물 추가

    // 사진 저장
}

data class ItemResponseDto(
    val result: List<Item>
): BaseResponse()

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