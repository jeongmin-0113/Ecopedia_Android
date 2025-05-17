package com.ecopedia.ecopedia_android.data.repository

import android.util.Log
import coil3.Bitmap
import com.ecopedia.ecopedia_android.data.source.remote.EncyclopediaService
import com.ecopedia.ecopedia_android.utils.FormDataUtil
import com.ecopedia.ecopedia_android.utils.NetworkCallback
import com.ecopedia.ecopedia_android.utils.isErrorSuspend
import com.ecopedia.ecopedia_android.utils.isSuccessSuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EncyclopediaRepository @Inject constructor(
    private val service: EncyclopediaService
) {
    fun getHomeData() = flow {
        NetworkCallback(
            service.getHomeData()
        ).processResponse()
            .isSuccessSuspend {
                emit(it.result)
            }.isErrorSuspend {
                throw it
            }
    }.flowOn(Dispatchers.IO)

    fun getAllItems() = flow {
        NetworkCallback(
            service.getAllItems()
        ).processResponse()
            .isSuccessSuspend {
                // 성공 로직
                Log.d("도감 데이터 가져오기", "가져오기 성공")
                emit(it.result)
            }.isErrorSuspend {
                // 오류 로직
                throw it
            }
    }.flowOn(Dispatchers.IO)

    fun creatureValidation(
        file: Bitmap,
        latitude: String,
        lognitude: String
    ) = flow {
        NetworkCallback(
            service.creatureValidation(
                latitude = FormDataUtil.getTextBody("latitude", latitude),
                longitude = FormDataUtil.getTextBody("longitude", lognitude),
                file = FormDataUtil.getImageBody(
                    file, "file", "${System.currentTimeMillis()}"
                ),
            )
        ).processResponse()
            .isSuccessSuspend {
                emit(it.isSuccess)
            }.isErrorSuspend {
                throw it
            }
    }.flowOn(Dispatchers.IO)


    fun creatureSave(
        file: Bitmap,
        latitude: String,
        lognitude: String
    ) = flow {
        NetworkCallback(
            service.creatureSave(
                latitude = FormDataUtil.getTextBody("latitude", latitude),
                longitude = FormDataUtil.getTextBody("lognitude", lognitude),
                file = FormDataUtil.getImageBody(
                    file, "file", "${System.currentTimeMillis()}"
                ),
            )
        ).processResponse()
            .isSuccessSuspend {
                emit(it.isSuccess)
            }.isErrorSuspend {
                throw it
            }
    }.flowOn(Dispatchers.IO)

}